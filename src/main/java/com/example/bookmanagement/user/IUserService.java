package com.example.bookmanagement.user;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

     User createUser(User user);

     ResponseEntity<String> loanBook(Long userId, Long bookId);

     void addUser(String name, String email, String phone);

     List<User> getAllUsers();

}
