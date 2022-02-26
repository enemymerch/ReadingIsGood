package com.mcan.rig.exception;

public class CustomerBadCreateRequestBodyException extends RuntimeException {

    public CustomerBadCreateRequestBodyException () {
        super("Given request is not acceptable to create user");
    }
}
