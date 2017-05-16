package com.bookhut.controllers;

import com.bookhut.models.bindingModels.AddBookModel;
import com.bookhut.service.BookService;
import com.bookhut.serviceImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet("/addbook")
public class AddBookController extends HttpServlet {

    private BookService bookService;

    public AddBookController() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/addbook.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addButton = req.getParameter("add").toString();
        if(addButton != null){
            String title = req.getParameter("title").toString();
            String author = req.getParameter("author").toString();
            int pages = Integer.parseInt(req.getParameter("pages"));
            AddBookModel addBookModel = new AddBookModel(title, author, pages);
            this.bookService.saveBook(addBookModel);
        }

        req.getRequestDispatcher("/templates/addbook.jsp").forward(req, resp);

    }
}
