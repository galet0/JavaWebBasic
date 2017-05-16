package com.bookhut.repositories;

import com.bookhut.entities.User;

/**
 * Created by asus on 20.2.2017 г..
 */
public interface UserRepository {

    void createUser(User user);

    User findByUsernameAndPassword(String username, String password);
}
