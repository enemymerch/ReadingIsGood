package com.mcan.rig.exception;

public class UsernameAlreadyExitsException extends RuntimeException {

    public UsernameAlreadyExitsException () {
        super("Username already exits.");
    }
}
