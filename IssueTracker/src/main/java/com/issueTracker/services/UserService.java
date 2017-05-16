package com.IssueTracker.services;


import com.IssueTracker.models.bindingModels.LoggedUserModel;
import com.IssueTracker.models.bindingModels.RegisterUserModel;

public interface UserService {

    void create(RegisterUserModel registerUserModel);

    RegisterUserModel findByUsername(String username);

    LoggedUserModel findByUsernameAndPassword(String username, String password);

}
