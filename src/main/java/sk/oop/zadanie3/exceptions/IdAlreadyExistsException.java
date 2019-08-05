package sk.oop.zadanie3.exceptions;

public class IdAlreadyExistsException extends Exception { // vynimka na osetrenie duplicitneho id miesta alebo prechodu
    public IdAlreadyExistsException() { super(); }
    public IdAlreadyExistsException(String message) { super(message); }
}
