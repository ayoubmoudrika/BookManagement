package com.example.bookmanagement.loan;

import com.example.bookmanagement.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoanRepository extends JpaRepository<Loan, Long> {
}
