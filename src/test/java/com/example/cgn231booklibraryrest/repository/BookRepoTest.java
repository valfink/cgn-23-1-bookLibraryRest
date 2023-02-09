package com.example.cgn231booklibraryrest.repository;

import com.example.cgn231booklibraryrest.model.Book;
import com.example.cgn231booklibraryrest.model.BookFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BookRepoTest {
    Book b1, b2, b1Updated;
    BookRepo bookRepo;

    @BeforeEach
    public void setUp(){
        b1 = new Book("ISB425", "ISBN78", "AmazingBook", "Johannes", BookFormat.E_BOOK);
        b2 = new Book ("id69420", "ISBN2", "IncredibleBook", "Walle",BookFormat.HARD_COVER);
        b1Updated = new Book("ISB425", "ISBN78", "BestBookEver", "Johannes", BookFormat.HARD_COVER);
        bookRepo = new BookRepo(new HashMap<>(Map.of(b1.id(), b1, b2.id(), b2)));
    }

    @Test
    void getBookByISBN() {
        //GIVEN
        Book expected = b1;
        //WHEN
        Book actual = bookRepo.getBookByISBN(b1.isbn()).orElse(null);
        //THEN
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void getBookByISBNEmpty(){
        //GIVEN
        //WHEN
        Book actual = bookRepo.getBookByISBN("").orElse(null);
        //THEN
        assertNull(actual);
    }
    @Test
    void putBookTest(){
        //GIVEN
        Book expected = b1Updated;
        //WHEN
        Book actual = bookRepo.putBook(b1Updated, b1Updated.isbn()).orElse(null);
        //THEN
        assertEquals(expected, actual);
    }
    @Test
    void deleteBookByIsbn(){
        //GIVEN
        Book expectedDeleted = b1;
        //WHEN
        Book actual = bookRepo.deleteBook(b1.isbn()).orElse(null);
        //THEN
        assertEquals(expectedDeleted, actual);
    }
}