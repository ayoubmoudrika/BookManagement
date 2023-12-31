package com.example.bookmanagement.loan;

import com.example.bookmanagement.Book.Book;
import com.example.bookmanagement.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loan")
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    private LocalDate loanDate;
    private LocalDate dueDate;
    private String userName;
    private String bookTitle;

    private boolean isLate;

    public Loan() {
    }

    public void isLate() {
        LocalDate todayDate = LocalDate.now();

        if ( todayDate.isAfter( dueDate ) ) {
            isLate = true;
        } else {
            isLate = false;
        }
    }


}
