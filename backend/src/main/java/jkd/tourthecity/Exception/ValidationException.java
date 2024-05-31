package jkd.tourthecity.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String reason) {
        super("Validation failed for reason: " + reason);
    }

    public ValidationException() {
    }
}
