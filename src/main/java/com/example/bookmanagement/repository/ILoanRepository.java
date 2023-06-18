package com.example.bookmanagement.repository;

import com.example.bookmanagement.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoanRepository extends JpaRepository<Loan, Long> {
}
