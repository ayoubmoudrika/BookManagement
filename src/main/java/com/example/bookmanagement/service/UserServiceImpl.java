package com.example.bookmanagement.service;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.User;
import com.example.bookmanagement.repository.IBookRepository;
import com.example.bookmanagement.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IBookRepository bookRepository;

    public UserServiceImpl(IUserRepository userRepository, IBookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public void loanBook(Long userId, Long bookId) {

        Optional<User> userOptional = userRepository.findById(userId);

        if ( userOptional.isPresent() ) {

            Optional<Book> bookOptional = bookRepository.findById(bookId);

            if ( bookOptional.isPresent() ) {

                bookOptional.get().setStatus("LOANED");
                bookRepository.save( bookOptional.get() );

                userOptional.get().getLoanedBooks().add( bookOptional.get() );
                userRepository.save( userOptional.get() );


            }
        }
    }

    @Override
    public void addUser(String name, String email, String phone) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
