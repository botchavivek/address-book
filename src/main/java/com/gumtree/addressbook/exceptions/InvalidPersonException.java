package com.gumtree.addressbook.exceptions;

public class InvalidPersonException extends RuntimeException {
    public InvalidPersonException() {
        super();
    }

    public InvalidPersonException(String message) {
        super(message);
    }
}

