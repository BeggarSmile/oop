package model;

import java.util.Objects;

public class NoRentedSpaceException extends RuntimeException {

    public NoRentedSpaceException() {
        super();
    }

    public NoRentedSpaceException(String message) {
        super(message);
    }

    public NoRentedSpaceException(String message, Exception exception) {
        super(message, exception);
    }
}
