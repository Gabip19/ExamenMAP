package org.examen.examenmap.domain.validators.exceptions;

public class ExempluValidationException extends ValidationException {
    public ExempluValidationException() {
        super();
    }

    public ExempluValidationException(String message) {
        super(message);
    }

    public ExempluValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExempluValidationException(Throwable cause) {
        super(cause);
    }

    protected ExempluValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
