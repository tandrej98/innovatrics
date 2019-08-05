package sk.oop.zadanie3.exceptions;

public class PlaceDoesNotExistException extends Exception { // vynimka na osetrenie neexistujuceho miesta
    public PlaceDoesNotExistException() { super(); }
    public PlaceDoesNotExistException(String message) { super(message); }
}
