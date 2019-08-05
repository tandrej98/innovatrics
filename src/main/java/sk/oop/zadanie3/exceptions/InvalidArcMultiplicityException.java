package sk.oop.zadanie3.exceptions;

public class InvalidArcMultiplicityException extends Exception { // vynimka na osetrenie nasobnosti hrany mensej ako 1
    public InvalidArcMultiplicityException() { super(); }
    public InvalidArcMultiplicityException(String message) { super(message); }
}
