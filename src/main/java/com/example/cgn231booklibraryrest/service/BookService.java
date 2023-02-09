package com.example.cgn231booklibraryrest.service;

import com.example.cgn231booklibraryrest.repository.BookRepo;
import com.example.cgn231booklibraryrest.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    public List<Book> listBooks() {
        return bookRepo.listBooks();
    }

    public Book addBook(Book addedBook) {
        Book bookWithId = new Book(IDService.generateId(), addedBook.isbn(), addedBook.title(), addedBook.author(), addedBook.bookFormat());
        bookRepo.addBook(bookWithId);
        return bookWithId;

    }
    public Book getBookByISBN(String isbn) {
        return bookRepo.getBookByISBN(isbn).orElseThrow(NoSuchElementException::new);
    }
    //TODO: update
    public Book putBook(Book updatedBook, String isbn) {
        Optional<Book> book = bookRepo.getBookByISBN(isbn);
        return null;
    }
}
