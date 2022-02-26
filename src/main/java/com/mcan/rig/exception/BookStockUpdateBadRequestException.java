package com.mcan.rig.exception;

public class BookStockUpdateBadRequestException extends RuntimeException {

    public BookStockUpdateBadRequestException () {
        super("New book stock cannot be null or lower than zero.");
    }
}
