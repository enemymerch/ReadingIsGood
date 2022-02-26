package com.mcan.rig.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException (Long identifier) {
        super("Book not found with identifier: " + identifier);
    }
}
