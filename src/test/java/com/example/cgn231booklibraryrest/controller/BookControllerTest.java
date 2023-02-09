package com.example.cgn231booklibraryrest.controller;

import com.example.cgn231booklibraryrest.model.Book;
import com.example.cgn231booklibraryrest.model.BookFormat;
import com.example.cgn231booklibraryrest.repository.BookRepo;
import com.example.cgn231booklibraryrest.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    BookRepo bookRepo;

    Book b1, b2;
    Map<String, Book> bookMap;

    @BeforeEach
    void setUp() {
        b1 = new Book("ISB425", "ISBN78", "AmazingBook", "Johannes", BookFormat.E_BOOK);
        b2 = new Book ("id69420", "ISBN2", "IncredibleBook", "Walle",BookFormat.HARD_COVER);
        bookMap = new HashMap<>(Map.of(b1.id(), b1, b2.id(), b2));
    }

    @Test
    @DirtiesContext
    void getBooks() throws Exception {
        // GIVEN
        bookRepo.addBook(b1);
        bookRepo.addBook(b2);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                [
                    {
                        "id": "ISB425",
                        "isbn": "ISBN78",
                        "title": "AmazingBook",
                        "author": "Johannes",
                        "bookFormat": "E_BOOK"
                    },
                    {
                        "id": "id69420",
                        "isbn": "ISBN2",
                        "title": "IncredibleBook",
                        "author": "Walle",
                        "bookFormat": "HARD_COVER"
                    }
                ]
                """));

        // THEN
    }
}