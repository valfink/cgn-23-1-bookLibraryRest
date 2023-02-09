package com.example.cgn231booklibraryrest.service;

import com.example.cgn231booklibraryrest.repository.BookRepo;
import com.example.cgn231booklibraryrest.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    public List<Book> listBooks() {
        return bookRepo.listBooks();
    }
}
