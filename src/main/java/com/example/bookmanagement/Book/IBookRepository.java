package com.example.bookmanagement.Book;

import com.example.bookmanagement.Book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByTitle(String title);
    List<Book> findBookByAuthor(String author);

    List<Book> findBookByGenre(String genre);

    List<Book> findBookByPublisher(String publisher);
}
