package com.example.bookmanagement.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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

    public Book(String title, String author) {
    }
}
