package Exceptions;

public class Exceptions {

    public static class InconsistentNumberOfBracketsException extends RuntimeException {
        public InconsistentNumberOfBracketsException() {
            super();
        }
    }
    public static class InvalidTokenException extends RuntimeException {
        public InvalidTokenException(String message) {
            super(message);
        }
    }

    public static class InvalidOperatorImplement extends RuntimeException {
        public InvalidOperatorImplement() {
            super();
        }
    }
}
