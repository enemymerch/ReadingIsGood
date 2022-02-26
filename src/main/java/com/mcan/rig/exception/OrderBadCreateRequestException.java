package com.mcan.rig.exception;

public class OrderBadCreateRequestException extends RuntimeException {

    public OrderBadCreateRequestException () {
        super("Request is not acceptable for creating order.");
    }
}
