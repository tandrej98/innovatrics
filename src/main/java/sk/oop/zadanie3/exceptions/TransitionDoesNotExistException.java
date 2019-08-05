package sk.oop.zadanie3.exceptions;

public class TransitionDoesNotExistException extends Exception {// vynimka na osetrenie neexistujuceho prechodu
    public TransitionDoesNotExistException() { super(); }
    public TransitionDoesNotExistException(String message) { super(message); }
}
