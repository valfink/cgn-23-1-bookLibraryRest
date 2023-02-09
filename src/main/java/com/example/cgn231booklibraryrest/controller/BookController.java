package com.example.cgn231booklibraryrest.controller;

import com.example.cgn231booklibraryrest.model.Book;
import com.example.cgn231booklibraryrest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping ("/books")
    public List<Book> getBooks() {
        return bookService.listBooks();
    }

    @GetMapping("books/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByISBN(isbn);
    }

    @PostMapping("/")
    public Book postBook(@RequestBody Book incomingBook){
        return bookService.addBook(incomingBook);
    }

    @PutMapping("/")
    public Book putBook(@RequestParam String isbn, @RequestBody Book putBook) {
        return bookService.putBook(putBook, isbn);
    }
    @DeleteMapping("/")
    public Book deleteBook(@RequestParam String isbn) {
        return bookService.deleteBookByIsbn(isbn);
    }


}
