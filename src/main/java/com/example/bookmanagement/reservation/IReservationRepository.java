package com.example.bookmanagement.reservation;

import com.example.bookmanagement.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}
