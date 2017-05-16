package com.pizzaForum.repository;


import com.pizzaForum.entities.User;

public interface UserRepository {

    void create(User user);

    long getTotalUsers();

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
