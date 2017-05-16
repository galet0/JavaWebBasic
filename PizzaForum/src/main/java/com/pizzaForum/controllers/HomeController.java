package com.pizzaForum.controllers;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.GetMapping;
import com.pizzaForum.constants.Constants;

import javax.ejb.Stateless;

@Stateless
@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(){
        return Constants.HOME_PAGE;
    }
}
