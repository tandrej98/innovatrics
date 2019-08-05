package sk.oop.zadanie3.exceptions;

public class TransitionCannotFireException extends  Exception{ // vynimka na osetrenie nevyhovujucivh podmienok na spustenie
    public TransitionCannotFireException() { super(); }
    public TransitionCannotFireException(String message) { super(message); }
}
