package com.fort.conf;

public class TooManyLoginAttempts extends RuntimeException {
    public TooManyLoginAttempts(String message) {
        super(message);
    }
}
