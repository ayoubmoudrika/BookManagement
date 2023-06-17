package com.example.bookmanagement.service;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public void addBook(String title, String author, String genre, int height, String publisher) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setHeight(height);
        book.setPublisher(publisher);
        bookRepository.save(book);
    }

    public List<Book> searchBookByTitle(String query){
        return bookRepository.findBookByTitle(query);
    }
}
