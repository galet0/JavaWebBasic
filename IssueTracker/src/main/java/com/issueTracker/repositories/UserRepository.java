package com.IssueTracker.repositories;

import com.IssueTracker.entities.User;

public interface UserRepository {

    void create(User user);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    long getTotalUsers();
}
