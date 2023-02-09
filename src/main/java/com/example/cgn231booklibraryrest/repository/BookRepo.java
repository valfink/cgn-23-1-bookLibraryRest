package com.example.cgn231booklibraryrest.repository;

import com.example.cgn231booklibraryrest.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
@RequiredArgsConstructor
public class BookRepo {
    private final Map<String, Book> books;

    public List<Book> listBooks() {
        return books.values().stream().toList();
    }

    public Book addBook(Book book) {
        books.put(book.id(), book);
        return book;
    }
}
