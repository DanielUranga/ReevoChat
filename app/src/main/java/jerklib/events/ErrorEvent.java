package jerklib.events;

import jerklib.Session;

public class ErrorEvent extends IRCEvent {
    private final ErrorType errorType;

    public ErrorEvent(String data, Session session, ErrorType errorType) {
        super(data, session, Type.ERROR);
        this.errorType = errorType;
    }

    /**
     * Get the error type
     *
     * @return ErrorType
     */
    public ErrorType getErrorType() {
        return errorType;
    }

    public enum ErrorType {
        NUMERIC_ERROR,
        UNRESOLVED_HOSTNAME,
        GENERIC
    }

}
