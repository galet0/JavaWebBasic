package com.bookhut.service;

import com.bookhut.models.bindingModels.AddBookModel;
import com.bookhut.models.viewModels.ViewBookModel;

import java.util.List;

/**
 * Created by asus on 20.2.2017 г..
 */
public interface BookService {

    void saveBook(AddBookModel addBookModel);

    List<ViewBookModel> getAllBooks();

    ViewBookModel findBookByTitle(String title);

    void deleteBookByTitle(String title);
}
