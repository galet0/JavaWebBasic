package com.bookhut.serviceImpl;

import com.bookhut.entities.User;
import com.bookhut.models.bindingModels.LoginModel;
import com.bookhut.repositories.UserRepository;
import com.bookhut.repositoriesImpl.UserRepositoryImpl;
import com.bookhut.service.UserService;
import org.modelmapper.ModelMapper;


public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    public UserServiceImpl() {
        this.modelMapper = new ModelMapper();
        this.userRepository = UserRepositoryImpl.getInstance();
    }

    @Override
    public void createUser(LoginModel loginModel) {
        User user = this.modelMapper.map(loginModel, User.class);
        this.userRepository.createUser(user);
    }

    @Override
    public LoginModel findByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username, password);
        LoginModel loginModel = null;
        if(user != null){
            loginModel = this.modelMapper.map(user, LoginModel.class);
        }
        return loginModel;
    }
}
