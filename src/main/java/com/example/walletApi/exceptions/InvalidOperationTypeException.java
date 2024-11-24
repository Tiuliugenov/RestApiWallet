package com.example.walletApi.exceptions;

public class InvalidOperationTypeException extends RuntimeException {
    public InvalidOperationTypeException(String message) {
        super(message);  // Передаем переданное сообщение, чтобы оно выводилось
    }
}