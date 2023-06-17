package com.example.bookmanagement.controller;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.service.BookServiceImpl;
import com.example.bookmanagement.service.IBookService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            bookService.addBook(title, author, genre, height, publisher);
    }

    @GetMapping("/loadBooks")
    public void loadBooks() {
        String csvFile = "src/main/book.csv";

        List<String[]> rows = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                rows.add(line);
                addBook(line[0], line[1], line[2], Integer.parseInt(line[3]), line[4]);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
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
