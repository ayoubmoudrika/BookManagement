package com.example.bookmanagement.Book;

import com.example.bookmanagement.util.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
public class BookController {
    private final IBookService bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) throws IOException {

        this.bookService = bookService;
        loadBooks();
    }

    @PostMapping("/addBook")
    public void addBook(@RequestParam String title, String author, String genre, int height, String publisher) throws IOException {
        bookService.addBook( title, author, genre, height, publisher, "AVAILABLE" );
    }

    @PutMapping("/updateBook/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        try {
            bookService.updateBook( id, updatedBook );
        } catch ( BookNotFoundException ex ) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, ex.getMessage(), ex );
        }
    }


    public void loadBooks() throws IOException {

        List<String[]> rows = CSVParser.parseCSV("src/main/book.csv");

        for (int i = 0; i < rows.size() ; i++) {

            bookService.addBook( rows.get(i)[0],rows.get(i)[1],rows.get(i)[2],
                    Integer.parseInt(rows.get(i)[3]),rows.get(i)[4], "AVAILABLE" );

        }

    }







}
