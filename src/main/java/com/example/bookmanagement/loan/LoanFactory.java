package com.example.bookmanagement.loan;

import com.example.bookmanagement.Book.Book;
import com.example.bookmanagement.user.User;

import java.time.LocalDate;

public class LoanFactory {

    public static Loan createLoan(User user, Book book, LocalDate loanDate, LocalDate dueDate, String username, String bookTitle){
        return new Loan().builder()
                .user( user )
                .book( book )
                .loanDate( loanDate )
                .dueDate( dueDate )
                .userName( username )
                .bookTitle( bookTitle )
                .build();
    }
}
