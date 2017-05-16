package com.pizzaForum.controllers;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import com.pizzaForum.constants.Constants;
import com.pizzaForum.constants.Message;
import com.pizzaForum.models.bindingModels.LoginUserModel;
import com.pizzaForum.models.bindingModels.RegisterUserModel;
import com.pizzaForum.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
@Controller
public class UserController {

    @Inject
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(){
        return Constants.REGISTER_PAGE;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterUserModel userRegisterModel, Model model){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<RegisterUserModel>> constraints = validator.validate(userRegisterModel);
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<RegisterUserModel> constraint : constraints) {
            errors.add(constraint.getMessage());
        }

        if(!userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())){
            errors.add(Message.PASSWORD_NOT_MATCH);
        }

        String username = userRegisterModel.getUsername();
        RegisterUserModel foundUser = this.userService.findByUsername(username);
        if(foundUser != null){
            errors.add(Message.EXISTING_USER);
        }

        if(errors.size() > 0){
            model.addAttribute("errors", errors);
            return Constants.REGISTER_PAGE;
        }

        this.userService.create(userRegisterModel);
        return Constants.REDIRECT_LOGIN;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return Constants.LOGIN_PAGE;
    }

    @PostMapping("/login")
    public String loginUser(HttpSession session, LoginUserModel loginUserModel, Model model){
        String username = loginUserModel.getUsername();
        String password = loginUserModel.getPassword();
        LoginUserModel loggedUser = this.userService.findByUsernameAndPassword(username, password);
        List<String> errors = new ArrayList<>();
        if(loggedUser == null){
            errors.add(Message.INVALID_LOGIN_DETAILS);
            return Constants.LOGIN_PAGE;
        }

        session.setAttribute("user", loggedUser);

        return Constants.REDIRECT_HOME;
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session){
        session.invalidate();
        return Constants.REDIRECT_HOME;
    }
}
