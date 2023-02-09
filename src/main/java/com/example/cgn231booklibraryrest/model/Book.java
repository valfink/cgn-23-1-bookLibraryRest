package com.example.cgn231booklibraryrest.model;

public record Book(
        String id,
        String isbn,
        String title,
        String author,
        BookFormat bookFormat
) {
}
