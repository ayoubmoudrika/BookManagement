package com.example.bookmanagement.service;

import com.example.bookmanagement.model.Book;

import java.util.List;


public interface IBookService {

    void addBook(String title, String author, String genre, int height, String publisher, String status);

    boolean deleteBookById(Long id);

    List<Book> searchBookByTitle(String query);

    List<Book> searchBookByAuthor(String query);

    List<Book> searchBookByGenre(String query);

    List<Book> searchBookByPublisher(String query);

    List<Book> getAllBooks();
    void updateBook(Long id, Book updatedBook);

    void getLoanedBooks(Long id);

    List<Book> getAllLoanedBooks();
}
