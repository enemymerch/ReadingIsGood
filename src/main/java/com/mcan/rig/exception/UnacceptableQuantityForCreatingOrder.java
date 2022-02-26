package com.mcan.rig.exception;

public class UnacceptableQuantityForCreatingOrder extends RuntimeException {

    public UnacceptableQuantityForCreatingOrder () {
        super("Unacceptable quantity");
    }
}
