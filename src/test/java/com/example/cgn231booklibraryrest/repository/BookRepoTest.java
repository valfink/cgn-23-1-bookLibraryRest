package com.example.cgn231booklibraryrest.repository;

import com.example.cgn231booklibraryrest.model.Book;
import com.example.cgn231booklibraryrest.model.BookFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BookRepoTest {

    @Test
    void getBookByISBN() {
        //GIVEN
        Book b1 = new Book("ISB425", "ISBN78", "AmazingBook", "Johannes", BookFormat.E_BOOK);
        Book b2 = new Book ("id69420", "ISBN2", "IncredibleBook", "Walle",BookFormat.HARD_COVER);
        BookRepo bookRepo = new BookRepo(new HashMap<>(Map.of(b1.id(), b1, b2.id(), b2)));
        Book expected = b1;
        //WHEN
        Book actual = bookRepo.getBookByISBN(b1.isbn()).orElse(null);
        //THEN
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void getBookByISBNEmpty(){
        //GIVEN
        Book b1 = new Book("ISB425", "ISBN78", "AmazingBook", "Johannes", BookFormat.E_BOOK);
        Book b2 = new Book ("id69420", "ISBN2", "IncredibleBook", "Walle",BookFormat.HARD_COVER);
        BookRepo bookRepo = new BookRepo(new HashMap<>(Map.of(b1.id(), b1, b2.id(), b2)));
        //WHEN
        Book actual = bookRepo.getBookByISBN("").orElse(null);
        //THEN
        assertNull(actual);
    }
}