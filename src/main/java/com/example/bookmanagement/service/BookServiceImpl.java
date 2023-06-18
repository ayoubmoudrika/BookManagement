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
    public void addBook(String title, String author, String genre, int height, String publisher, String status) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setHeight(height);
        book.setPublisher(publisher);
        book.setStatus(status);
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

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void updateBook(Long id, Book updatedBook) {

        // Retrieve the existing book from the database
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        // Update the attributes of the existing book with the values from the updated book
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setHeight(updatedBook.getHeight());
        existingBook.setPublisher(updatedBook.getPublisher());
        // Update other attributes as needed

        // Save the updated book
        bookRepository.save(existingBook);
    }


}
