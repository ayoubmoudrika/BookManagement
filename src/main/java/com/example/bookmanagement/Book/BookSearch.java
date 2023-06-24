package com.example.bookmanagement.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookSearch {

    public List<Book> searchBooksByRegex(String substring, List<Book> bookCollection) {
        List<Book> searchResults = new ArrayList<>();

        Pattern pattern = Pattern.compile(".*" + substring + ".*", Pattern.CASE_INSENSITIVE);

        long startTime = System.currentTimeMillis();

        for (Book book : bookCollection) {
            if (matchesRegex(pattern, book.getTitle()) ||
                    matchesRegex(pattern, book.getAuthor()) ||
                    matchesRegex(pattern, book.getGenre())) {
                searchResults.add(book);
            }
        }

        long endTime = System.currentTimeMillis();
        long searchTime = endTime - startTime;
        System.out.println("Search time: " + searchTime + " milliseconds");

        return searchResults;
    }

    private boolean matchesRegex(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

}

