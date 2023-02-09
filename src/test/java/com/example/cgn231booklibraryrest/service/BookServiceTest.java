package com.example.cgn231booklibraryrest.service;

import com.example.cgn231booklibraryrest.model.BookFormat;
import com.example.cgn231booklibraryrest.repository.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.cgn231booklibraryrest.model.Book;

import javax.swing.text.html.Option;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {
    Book b1, b2, b1Updated;
    Map<String, Book> bookMap;
    List<Book> bookList;
    BookRepo bookRepo;
    BookService bookService;

    @BeforeEach
    void setUp() {
        b1 = new Book("ISB425", "ISBN78", "AmazingBook", "Johannes", BookFormat.E_BOOK);
        b2 = new Book ("id69420", "ISBN2", "IncredibleBook", "Walle",BookFormat.HARD_COVER);
        b1Updated = new Book("ISB425", "ISBN78", "BestBookEver", "Johannes", BookFormat.HARD_COVER);
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
        assertThrows(NoSuchElementException.class, () -> bookService.getBookByISBN(b1.isbn()));
        verify(bookRepo).getBookByISBN(b1.isbn());
    }
    @Test
    void addBookTest(){
        //GIVEN
        Book expectedBook = b1;
        String isbnExpected= expectedBook.isbn();
        String titleExpected= expectedBook.title();
        String authorExpected= expectedBook.author();
        BookFormat expectedFormat = expectedBook.bookFormat();
        //WHEN
        Book actualBook = bookService.addBook(b1);
        String isbnActual= actualBook.isbn();
        String titleActual= actualBook.title();
        String authorActual= actualBook.author();
        BookFormat actualFormat = actualBook.bookFormat();
        //THEN
        assertEquals(isbnExpected, isbnActual);
        assertEquals(titleExpected, titleActual);
        assertEquals(authorExpected, authorActual);
        assertEquals(expectedFormat, actualFormat);
    }

    @Test
    void deleteBookTest(){
        Book expectedBook = b1;
        String isbnExpected= expectedBook.isbn();
        String titleExpected= expectedBook.title();
        String authorExpected= expectedBook.author();
        BookFormat expectedFormat = expectedBook.bookFormat();
        //WHEN
        when(bookRepo.deleteBook(b1.isbn())).thenReturn(Optional.of(b1));
        Book actualBook = bookService.deleteBookByIsbn(b1.isbn());
        String isbnActual= actualBook.isbn();
        String titleActual= actualBook.title();
        String authorActual= actualBook.author();
        BookFormat actualFormat = actualBook.bookFormat();
        //THEN
        assertEquals(isbnExpected, isbnActual);
        assertEquals(titleExpected, titleActual);
        assertEquals(authorExpected, authorActual);
        assertEquals(expectedFormat, actualFormat);
    }
    @Test
    void putBookTest(){
        //GIVEN
        Book expectedBook = b1Updated;
        String isbnExpected= expectedBook.isbn();
        String titleExpected= expectedBook.title();
        String authorExpected= expectedBook.author();
        BookFormat expectedFormat = expectedBook.bookFormat();
        //WHEN
        when(bookRepo.putBook(b1Updated, b1Updated.isbn())).thenReturn(Optional.ofNullable(b1Updated));
        Book actualBook = bookService.putBook(b1Updated, b1Updated.isbn());
        String isbnActual= actualBook.isbn();
        String titleActual= actualBook.title();
        String authorActual= actualBook.author();
        BookFormat actualFormat = actualBook.bookFormat();
        //THEN
        assertEquals(isbnExpected, isbnActual);
        assertEquals(titleExpected, titleActual);
        assertEquals(authorExpected, authorActual);
        assertEquals(expectedFormat, actualFormat);
    }
}