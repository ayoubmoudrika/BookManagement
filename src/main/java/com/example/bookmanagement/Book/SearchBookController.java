package com.example.bookmanagement.Book;

import com.example.bookmanagement.Book.Book;
import com.example.bookmanagement.Book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RestController
public class SearchBookController {

    private IBookService bookService;

    @Autowired
    public SearchBookController(BookServiceImpl bookService) throws IOException {
        this.bookService = bookService;
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
    @GetMapping("/searchBooks")
    @ResponseBody
    public List<Book> searchBooks(@RequestParam("query") String query) {
        BookSearch bookSearch = new BookSearch();
        return bookSearch.searchBooksByRegex( query , bookService.getAllBooks());
    }

}
