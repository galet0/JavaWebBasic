package com.bookhut.repositories;

import com.bookhut.entities.Book;

import java.util.List;

/**
 * Created by asus on 20.2.2017 г..
 */
public interface BookRepository {

    void saveBook(Book book);

    List<Book> getAllBooks();

    void deleteBookByTitle(String title);

    Book findBookByTitle(String title);
}
