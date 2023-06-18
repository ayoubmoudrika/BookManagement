package com.example.bookmanagement.service;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.Loan;
import com.example.bookmanagement.model.User;
import com.example.bookmanagement.repository.IBookRepository;
import com.example.bookmanagement.repository.ILoanRepository;
import com.example.bookmanagement.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IBookRepository bookRepository;
    private final ILoanRepository loanRepository;


    public UserServiceImpl(IUserRepository userRepository, IBookRepository bookRepository, ILoanRepository loanRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public void loanBook(Long userId, Long bookId) {

        Optional<User> userOptional = userRepository.findById(userId);

        if ( userOptional.isPresent() ) {

            User user = userOptional.get();
            Optional<Book> bookOptional = bookRepository.findById(bookId);

            if ( bookOptional.isPresent() ) {

                Book book = bookOptional.get();
                bookOptional.get().setStatus("LOANED");
                bookRepository.save( bookOptional.get() );

                LocalDate loanDate = LocalDate.parse("2023-05-21");
                LocalDate dueDate = LocalDate.parse("2023-06-21");

                Loan loan = new Loan( );
                loan.setUser( user );
                loan.setBook( bookOptional.get() );
                loan.setLoanDate( loanDate) ;
                loan.setDueDate( dueDate );
                loan.setUserName( user.getName() );
                loan.setBookTitle( book.getTitle()) ;

                user.getLoans().add( loan );
                loanRepository.save( loan );
                userRepository.save( user );

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
