package com.IssueTracker.serviceImpl;

import com.IssueTracker.entities.User;
import com.IssueTracker.entities.enums.Role;
import com.IssueTracker.models.bindingModels.LoggedUserModel;
import com.IssueTracker.models.bindingModels.RegisterUserModel;
import com.IssueTracker.repositories.UserRepository;
import com.IssueTracker.services.UserService;
import com.IssueTracker.utils.modelParser.ModelParser;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
@Local(UserService.class)
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public void create(RegisterUserModel registerUserModel) {
        User user = this.modelParser.convert(registerUserModel, User.class);
        long totalUsers = this.userRepository.getTotalUsers();
        if(totalUsers == 0){
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        this.userRepository.create(user);
    }

    @Override
    public RegisterUserModel findByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        RegisterUserModel registerUserModel = null;

        if(user != null) {
            registerUserModel = this.modelParser.convert(user, RegisterUserModel.class);
        }

        return registerUserModel;
    }

    @Transactional
    @Override
    public LoggedUserModel findByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username, password);
        LoggedUserModel loggedUserModel = null;

        if(user != null) {
            loggedUserModel = this.modelParser.convert(user, LoggedUserModel.class);
        }

        return loggedUserModel;
    }
}
