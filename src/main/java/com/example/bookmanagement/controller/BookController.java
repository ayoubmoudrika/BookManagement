package com.example.bookmanagement.controller;

import com.example.bookmanagement.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class BookController {

    @Autowired
    private IBookService bookService;

    @PostMapping("/addBook")
    public void addBook(@RequestParam String title, String author) throws IOException {
            bookService.addBook(title, author);
    }

}
