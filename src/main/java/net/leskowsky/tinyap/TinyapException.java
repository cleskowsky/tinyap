package net.leskowsky.tinyap;

public class TinyapException extends RuntimeException {

    public TinyapException(Throwable cause) {
        super(cause);
    }

    public TinyapException(String message) {
        super(message);
    }
}
