package com.example.bookmanagement.user;

import com.example.bookmanagement.util.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
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

    @GetMapping("/{userId}/books/{bookId}/{loanStartingDate}/reserve")
    public ResponseEntity<String> reserveBook(@PathVariable Long userId, @PathVariable Long bookId, @PathVariable LocalDate loanStartingDate) {

        return userService.reserveBook( userId, bookId, loanStartingDate );

    }



    public void loadBooks() throws IOException {
        List<String[]> rows = CSVParser.parseCSV("src/main/users.csv");

        HashMap<Integer, String []> books = new HashMap<>();
        int key = 0;

        for ( String [] row: rows ) {
            books.put( key, row );
        }

        books.entrySet().stream().forEach (
                entry -> userService.addUser( entry.getValue()[0], entry.getValue()[1], entry.getValue()[2] )
        );
    }
}
