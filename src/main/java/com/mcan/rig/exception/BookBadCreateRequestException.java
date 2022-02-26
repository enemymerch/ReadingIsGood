package com.mcan.rig.exception;

public class BookBadCreateRequestException extends RuntimeException {

    public BookBadCreateRequestException () {
        super("Request is not acceptable for creating book.");
    }
}
