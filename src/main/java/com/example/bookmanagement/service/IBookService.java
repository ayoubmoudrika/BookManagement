package com.example.bookmanagement.service;

import com.example.bookmanagement.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IBookService {

    void addBook(String title, String author, String genre, int height, String publisher);

    List<Book> searchBookByTitle(String query);
}
