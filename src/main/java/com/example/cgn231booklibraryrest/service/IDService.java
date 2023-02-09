package com.example.cgn231booklibraryrest.service;

import java.util.UUID;

public class IDService {
    public static String generateId(){
        return UUID.randomUUID().toString();
    }
}
