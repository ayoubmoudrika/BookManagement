package com.example.bookmanagement.reservation;

import com.example.bookmanagement.Book.Book;
import com.example.bookmanagement.user.User;

import java.time.LocalDate;

public class ReservationFactory {

    public static Reservation createReservation(User user, Book book, LocalDate reservationDate, LocalDate loanStartingDate) {
        return new Reservation().builder()
                .user( user )
                .book( book )
                .reservationDate( reservationDate )
                .loanStartingDate( loanStartingDate )
                .build();

    }
}
