package com.example.bookmanagement.service;

import org.springframework.stereotype.Service;

@Service
public interface IBookService {

    void addBook(String title, String author);
}
