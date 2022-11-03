package com.example.demo;

public class DatFileException extends RuntimeException {
    public DatFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatFileException(String message) {
        super(message);
    }
}
