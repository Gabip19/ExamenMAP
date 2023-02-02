package org.examen.examenmap.domain.exceptions;

public class NoEmptyBedsException extends ValidationException {
    public NoEmptyBedsException() {
        super();
    }

    public NoEmptyBedsException(String message) {
        super(message);
    }

    public NoEmptyBedsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEmptyBedsException(Throwable cause) {
        super(cause);
    }

    protected NoEmptyBedsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
