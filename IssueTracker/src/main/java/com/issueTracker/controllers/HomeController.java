package com.IssueTracker.controllers;

import com.IssueTracker.constants.Constants;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.GetMapping;

import javax.ejb.Stateless;

@Stateless
@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(){
        return Constants.HOME_PAGE;
    }
}
