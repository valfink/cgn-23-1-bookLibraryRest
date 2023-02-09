package com.example.cgn231booklibraryrest.service;

import com.example.cgn231booklibraryrest.model.BookFormat;
import com.example.cgn231booklibraryrest.repository.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.cgn231booklibraryrest.model.Book;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {
    Book b1, b2;
    Map<String, Book> bookMap;
    List<Book> bookList;
    BookRepo bookRepo;
    BookService bookService;

    @BeforeEach
    void setUp() {
        b1 = new Book("ISB425", "ISBN78", "AmazingBook", "Johannes", BookFormat.E_BOOK);
        b2 = new Book ("id69420", "ISBN2", "IncredibleBook", "Walle",BookFormat.HARD_COVER);
        bookMap = new HashMap<>(Map.of(b1.id(), b1, b2.id(), b2));
        bookList = bookMap.values().stream().toList();
        bookRepo = mock(BookRepo.class);
        bookService = new BookService(bookRepo);
    }

    @Test
    void listBooks() {
        // GIVEN
        when(bookRepo.listBooks()).thenReturn(bookList);
        List<Book> expected = bookList;

        // WHEN
        List<Book> actual = bookService.listBooks();

        // THEN
        assertEquals(expected, actual);
    }
    @Test
    void getBookByISBN(){
        //GIVEN
        Book expected = b1;
        when(bookRepo.getBookByISBN(b1.isbn())).thenReturn(Optional.of(b1));
        //WHEN
        Book actual = bookService.getBookByISBN(b1.isbn());
        //THEN
        verify(bookRepo).getBookByISBN(b1.isbn());
        assertEquals(expected, actual);
    }
    @Test
    void getBookByISBN_exception(){
        //GIVEN
        when(bookRepo.getBookByISBN(b1.isbn())).thenReturn(Optional.empty());
        //THEN
        assertThrows(NoSuchElementException.class, () -> {
            bookService.getBookByISBN(b1.isbn());
        });
        verify(bookRepo).getBookByISBN(b1.isbn());
    }
}