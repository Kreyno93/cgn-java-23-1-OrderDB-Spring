package de.neuefische.cgnjava231orderdbspring.exceptions;

public class NoProductInOrderListException extends RuntimeException{
    public NoProductInOrderListException(String message) {
        super(message);
    }
}
