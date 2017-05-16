package com.pizzaForum.models.bindingModels;

import com.pizzaForum.constants.Message;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUserModel {

    @Size(min = 3)
    @Pattern(regexp = "[a-z0-9]+", message = Message.INVALID_USERNAME)
    private String username;

    @Pattern(regexp = "@")
    private String email;

    @Pattern(regexp = "[0-9]+")
    @Size(min = 4, max = 4)
    private String password;

    private String confirmPassword;

    public RegisterUserModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
