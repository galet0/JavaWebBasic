package com.bookhut.serviceImpl;

import com.bookhut.entities.Book;
import com.bookhut.models.bindingModels.AddBookModel;
import com.bookhut.models.viewModels.ViewBookModel;
import com.bookhut.repositories.BookRepository;
import com.bookhut.repositoriesImpl.BookRepositoryImpl;
import com.bookhut.service.BookService;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;


public class BookServiceImpl implements BookService {

    private ModelMapper modelMapper;

    private BookRepository bookRepository;

    public BookServiceImpl() {
        this.bookRepository = BookRepositoryImpl.getInstance();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void saveBook(AddBookModel addBookModel) {
        Book book = this.modelMapper.map(addBookModel, Book.class);
        this.bookRepository.saveBook(book);
    }

    @Override
    public List<ViewBookModel> getAllBooks() {
        List<Book> books = this.bookRepository.getAllBooks();
        List<ViewBookModel> viewBookModels = new ArrayList<>();
        for (Book book : books) {
            ViewBookModel viewBookModel = this.modelMapper.map(book, ViewBookModel.class);
            viewBookModels.add(viewBookModel);
        }
        return viewBookModels;
    }

    @Override
    public ViewBookModel findBookByTitle(String title) {
        Book book = this.bookRepository.findBookByTitle(title);
        ViewBookModel viewBookModel = this.modelMapper.map(book, ViewBookModel.class);
        return viewBookModel;
    }

    @Override
    public void deleteBookByTitle(String title) {
        this.bookRepository.deleteBookByTitle(title);
    }
}
