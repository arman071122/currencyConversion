package com.example.demo.Exception;

public class InvalidCurrencyException extends Exception {
    public InvalidCurrencyException(String message) {
        super(message);
    }
}