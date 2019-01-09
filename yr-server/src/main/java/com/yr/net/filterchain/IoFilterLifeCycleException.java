package com.yr.net.filterchain;

/**
 * The type Io filter life cycle exception.
 */
public class IoFilterLifeCycleException extends RuntimeException {
    private static final long serialVersionUID = -5542098881633506449L;

    /**
     * Instantiates a new Io filter life cycle exception.
     */
    public IoFilterLifeCycleException() {
    }

    /**
     * Instantiates a new Io filter life cycle exception.
     *
     * @param message the message
     */
    public IoFilterLifeCycleException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Io filter life cycle exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public IoFilterLifeCycleException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Io filter life cycle exception.
     *
     * @param cause the cause
     */
    public IoFilterLifeCycleException(Throwable cause) {
        super(cause);
    }
}
