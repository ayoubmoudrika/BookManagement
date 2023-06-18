package com.example.bookmanagement.service;

import com.example.bookmanagement.model.User;

import java.util.List;

public interface IUserService {

     User createUser(User user);

     void loanBook(Long userId, Long bookId);

     void addUser(String name, String email, String phone);

     List<User> getAllUsers();
}
