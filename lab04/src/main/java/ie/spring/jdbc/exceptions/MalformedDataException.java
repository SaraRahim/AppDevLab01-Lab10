package ie.spring.jdbc.exceptions;

public class MalformedDataException extends RuntimeException {
    public MalformedDataException(String message) {
        super(message);
    }
}
