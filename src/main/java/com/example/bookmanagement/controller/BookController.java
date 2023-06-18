package com.example.bookmanagement.controller;

import Util.CSVParser;
import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.service.BookNotFoundException;
import com.example.bookmanagement.service.BookServiceImpl;
import com.example.bookmanagement.service.IBookService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    private final IBookService bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }


    @PostMapping("/addBook")
    public void addBook(@RequestParam String title, String author, String genre, int height, String publisher) throws IOException {
            bookService.addBook(title, author, genre, height, publisher, "AVAILABLE");
    }




    @GetMapping("/loadBooks")
    public void loadBooks() throws IOException {

        List<String[]> rows = CSVParser.parseCSV("src/main/book.csv");

        for (int i = 0; i < rows.size() ; i++) {

            addBook(rows.get(i)[0],rows.get(i)[1],rows.get(i)[2], Integer.parseInt(rows.get(i)[3]),rows.get(i)[4]);

        }

    }

    @GetMapping("getAllBooks")
    @ResponseBody
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    public List<Book> getAllLoanedBooks(){
        return bookService.getAllLoanedBooks();
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBookById(id);

        if (deleted) {
            return ResponseEntity.ok("Book deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
    }

    @PutMapping("/updateBook/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        try {
            bookService.updateBook(id, updatedBook);
        } catch (BookNotFoundException ex) {
            // Handle the exception and return an appropriate response
            // For example, you can return a 404 Not Found response
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @GetMapping("/searchBookByAuthor")
    @ResponseBody
    public List<Book> searchBookByAuthor(@RequestParam("query") String query) {
        return bookService.searchBookByAuthor(query);
    }
    @GetMapping("/searchBookByTitle")
    @ResponseBody
    public List<Book> searchBookByTitle(@RequestParam("query") String query) {
        return bookService.searchBookByTitle(query);
    }

    @GetMapping("/searchBookByGenre")
    @ResponseBody
    public List<Book> searchBookByGenre(@RequestParam("query") String query) {
        return bookService.searchBookByGenre(query);
    }

    @GetMapping("/searchBookByPublisher")
    @ResponseBody
    public List<Book> searchBookByPublisher(@RequestParam("query") String query) {
        return bookService.searchBookByPublisher(query);
    }







}
