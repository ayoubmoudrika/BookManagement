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

    @GetMapping("/searchBooks")
    @ResponseBody
    public List<Book> searchBooks(@RequestParam("query") String query) {
        BookSearch bookSearch = new BookSearch();
        return bookSearch.searchBooksByRegex( query , bookService.getAllBooks());
    }

}
