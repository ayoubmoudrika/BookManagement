package com.example.bookmanagement.Book;

public class BookFactory {

    public static Book createBook(String title, String author, String genre, int height, String publisher, String status) {
        return new Book( title, author, genre, height, publisher, status );
    }
}
