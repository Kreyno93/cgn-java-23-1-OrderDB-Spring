package de.neuefische.cgnjava231orderdbspring.exceptions;

public class NoOrderWithIdException extends RuntimeException{
    public NoOrderWithIdException(String message) {
        super(message);
    }
}
