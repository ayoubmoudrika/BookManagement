package com.example.bookmanagement.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public void addBook(String title, String author, String genre, int height, String publisher, String status) {
        bookRepository.save( BookFactory.createBook( title, author, genre, height, publisher, status ) );
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

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void updateBook(Long id, Book updatedBook) {

        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setHeight(updatedBook.getHeight());
        existingBook.setPublisher(updatedBook.getPublisher());

        bookRepository.save(existingBook);
    }

    @Override
    public void getLoanedBooks(Long id) {

    }

    @Override
    public List<Book> getAllLoanedBooks() {
        return null;
    }

}
