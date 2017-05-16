package com.bookhut.controllers;

import com.bookhut.config.Config;
import com.bookhut.models.bindingModels.LoginModel;
import com.bookhut.service.UserService;
import com.bookhut.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signin")
public class SignInController extends HttpServlet {

    private UserService userService;

    public SignInController() {
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String signin = req.getParameter("signin");
        LoginModel loginModel = null;
        if(signin != null){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            loginModel = this.userService.findByUsernameAndPassword(username, password);

            if(loginModel != null){
                HttpSession session = req.getSession();
                session.setAttribute(Config.LOGIN_MODEL, loginModel);
                resp.sendRedirect("/");
            } else {
                req.getRequestDispatcher("/templates/signin.jsp").forward(req, resp);
            }
        }
    }
}
