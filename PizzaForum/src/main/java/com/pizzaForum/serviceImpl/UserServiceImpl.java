package com.pizzaForum.serviceImpl;

import com.pizzaForum.entities.User;
import com.pizzaForum.models.bindingModels.LoginUserModel;
import com.pizzaForum.models.bindingModels.RegisterUserModel;
import com.pizzaForum.repository.UserRepository;
import com.pizzaForum.service.UserService;
import com.pizzaForum.utils.modelParser.ModelParser;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Local(UserService.class)
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public void create(RegisterUserModel userRegisterModel) {
        User user = this.modelParser.convert(userRegisterModel, User.class);
        long countUsers = this.userRepository.getTotalUsers();
        if(countUsers == 0){
            user.setAdmin(true);
        } else {
            user.setAdmin(false);
        }

        this.userRepository.create(user);
    }

    @Override
    public RegisterUserModel findByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        RegisterUserModel userRegisterModel = this.modelParser.convert(user, RegisterUserModel.class);
        return userRegisterModel;
    }

    @Override
    public LoginUserModel findByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username, password);
        LoginUserModel loginUserModel = this.modelParser.convert(user, LoginUserModel.class);
        return loginUserModel;
    }
}
