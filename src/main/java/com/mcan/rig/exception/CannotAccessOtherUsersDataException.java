package com.mcan.rig.exception;

public class CannotAccessOtherUsersDataException extends RuntimeException {

    public CannotAccessOtherUsersDataException () {
        super("You cannot access other users data.");
    }

}
