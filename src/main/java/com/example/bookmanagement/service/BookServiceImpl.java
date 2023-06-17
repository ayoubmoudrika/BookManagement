package com.example.bookmanagement.service;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean deleteBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Book> searchBookByTitle(String query){
        return bookRepository.findBookByTitle(query);
    }

    @Override
    public List<Book> searchBookByAuthor(String query) {
        return bookRepository.findBookByAuthor(query);
    }

    public List<Book> searchBookByGenre(String query) {
        return bookRepository.findBookByGenre(query);
    }

    @Override
    public List<Book> searchBookByPublisher(String query) {
        return bookRepository.findBookByPublisher(query);
    }
}
