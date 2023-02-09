package com.example.cgn231booklibraryrest.repository;

import com.example.cgn231booklibraryrest.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


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
    public Optional<Book> getBookByISBN (String isbn){
        return books.values().stream()
                .filter(b->b.isbn().equals(isbn))
                .findFirst();
    }
    public Optional<Book> putBook(Book bookToPut, String isbn){
        Optional<Book> currentBook = books.values().stream()
                .filter(b->b.isbn().equals(isbn))
                .findFirst();
        if(currentBook.isPresent()){
        Book newBookInRepo = new Book(currentBook.get().id(), bookToPut.isbn(), bookToPut.title(), bookToPut.author(),
                bookToPut.bookFormat());
            books.put(currentBook.get().id(), newBookInRepo);
            return Optional.of(newBookInRepo);
        } else {
            return Optional.empty();
        }
    }
    public Optional<Book> deleteBook(String isbn) {
         Optional<Book> bookToDelete = books.values().stream()
                .filter(b->b.isbn().equals(isbn))
                .findFirst();
         books.remove(bookToDelete.get().id());
         return bookToDelete;


    }
}
