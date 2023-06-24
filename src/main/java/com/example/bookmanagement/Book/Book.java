package com.example.bookmanagement.Book;

import com.example.bookmanagement.loan.Loan;
import com.example.bookmanagement.reservation.Reservation;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="books")
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "genre")

    private String genre;

    @Column(name = "height")
    private int height;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "book")
    private List<Loan> loans = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Reservation> reservations = new ArrayList<>();

    public Book(String title, String author) {
    }

    public Book(String title, String author, String genre, int height, String publisher, String status) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.height = height;
        this.publisher = publisher;
        this.status = status;
    }

    public boolean isAvailable() {
        if ( getStatus().equals( "AVAILABLE" ) ){
            return true;
        } else {
            return false;
        }
    }

    public boolean isReserved(LocalDate loanStatingDate) {

        for (Reservation reservation: reservations) {

        }
        return false;
    }

}
