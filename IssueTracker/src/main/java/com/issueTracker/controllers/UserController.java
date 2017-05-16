package com.IssueTracker.controllers;

import com.IssueTracker.constants.Constants;
import com.IssueTracker.constants.Message;
import com.IssueTracker.models.bindingModels.LoggedUserModel;
import com.IssueTracker.models.bindingModels.LoginUserModel;
import com.IssueTracker.models.bindingModels.RegisterUserModel;
import com.IssueTracker.services.UserService;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;

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
    public String registerUser(@ModelAttribute RegisterUserModel registerUserModel, Model model){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<RegisterUserModel>> constraints = validator.validate(registerUserModel);
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<RegisterUserModel> constraint : constraints) {
            errors.add(constraint.getMessage());
        }

        if(!registerUserModel.getPassword().equals(registerUserModel.getConfirmPassword())){
            errors.add(Message.INVALID_CONFIRM_PASSWORD);
        }

        String currUsername = registerUserModel.getUsername();
        RegisterUserModel foundUser = this.userService.findByUsername(currUsername);
        if(foundUser != null){
            errors.add(Message.EXISTING_USER);
        }

        if(errors.size() > 0){
            model.addAttribute("errors", errors);
            return Constants.REGISTER_PAGE;
        }

        this.userService.create(registerUserModel);

        return Constants.REDIRECT_LOGIN;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return Constants.LOGIN_PAGE;
    }

    @PostMapping("/login")
    public String loginUser(HttpSession session, @ModelAttribute LoginUserModel loginUserModel, Model model){
        String username = loginUserModel.getUsername();
        String password = loginUserModel.getPassword();
        LoggedUserModel loggedUser = this.userService.findByUsernameAndPassword(username, password);

        if(loggedUser == null){
            model.addAttribute("error", Message.INVALID_LOGIN_DETAILS);
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
