package com.neoris.exception;

public class NeorisException extends Exception {

    public NeorisException(String message) {
        super(message);
    }

    public NeorisException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeorisException(Throwable cause) {
        super(cause);
    }

    protected NeorisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
