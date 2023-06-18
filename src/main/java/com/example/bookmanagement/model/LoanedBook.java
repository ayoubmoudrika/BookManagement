package com.example.bookmanagement.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="loanedBooks")
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoanedBook extends Book{

    @Column(name = "returnDate")
    private Date returnDate;

    @Column(name = "isLate")
    private boolean isLate;
}
