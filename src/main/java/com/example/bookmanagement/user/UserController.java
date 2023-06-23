package com.example.bookmanagement.user;

import com.example.bookmanagement.util.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) throws IOException {

        this.userService = userService;
        loadBooks();
    }

    @PostMapping("/addUser")
    public void addUser(@RequestParam String name, String email, String phone) throws IOException {
        userService.addUser(name, email, phone);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("getAllUsers")
    @ResponseBody
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}/books/{bookId}/loan")
    public ResponseEntity<String> loanBook(@PathVariable Long userId, @PathVariable Long bookId) {

        return userService.loanBook(userId, bookId);

    }

    public void loadBooks() throws IOException {

        List<String[]> rows = CSVParser.parseCSV("src/main/users.csv");

        for (int i = 0; i < rows.size() ; i++) {

            userService.addUser(rows.get(i)[0],rows.get(i)[1],rows.get(i)[2]);
        }

    }


}
