package com.yr.net.filter.logging;

/**
 * The enum Log level.
 */
public enum LogLevel {

    /**
     * {@link LogLevel} which logs messages on the TRACE level.
     */
    TRACE(5),

    /**
     * {@link LogLevel} which logs messages on the DEBUG level.
     */
    DEBUG(4),

    /**
     * {@link LogLevel} which logs messages on the INFO level.
     */
    INFO(3),

    /**
     * {@link LogLevel} which logs messages on the WARN level.
     */
    WARN(2),

    /**
     * {@link LogLevel} which logs messages on the ERROR level.
     */
    ERROR(1),

    /**
     * {@link LogLevel} which will not log any information
     */
    NONE(0);

    /** The internal numeric value associated with the log level */
    private int level;

    /**
     * Create a new instance of a LogLevel.
     * 
     * @param level The log level
     */
    private LogLevel(int level) {
        this.level = level;
    }

    /**
     * Gets level.
     *
     * @return The numeric value associated with the log level
     */
    public int getLevel() {
        return level;
    }
}