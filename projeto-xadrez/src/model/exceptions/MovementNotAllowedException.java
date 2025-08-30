package model.exceptions;

public class MovementNotAllowedException extends RuntimeException {
    public MovementNotAllowedException(String message) {
        super(message);
    }
}
