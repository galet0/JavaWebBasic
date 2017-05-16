package com.pizzaForum.service;


import com.pizzaForum.models.bindingModels.LoginUserModel;
import com.pizzaForum.models.bindingModels.RegisterUserModel;

public interface UserService {

    void create(RegisterUserModel userRegisterModel);

    RegisterUserModel findByUsername(String username);

    LoginUserModel findByUsernameAndPassword(String username, String password);
}
