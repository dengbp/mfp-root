package com.yr.net.filter.reqres;

import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * The type Request.
 */
public class Request {
    private final Object id;

    private final Object message;

    private final long timeoutMillis;

    private volatile Runnable timeoutTask;

    private volatile ScheduledFuture<?> timeoutFuture;

    private final BlockingQueue<Object> responses;

    private volatile boolean endOfResponses;

    /**
     * Instantiates a new Request.
     *
     * @param id            the id
     * @param message       the message
     * @param timeoutMillis the timeout millis
     */
    public Request(Object id, Object message, long timeoutMillis) {
        this(id, message, true, timeoutMillis);
    }

    /**
     * Instantiates a new Request.
     *
     * @param id               the id
     * @param message          the message
     * @param useResponseQueue the use response queue
     * @param timeoutMillis    the timeout millis
     */
    public Request(Object id, Object message, boolean useResponseQueue, long timeoutMillis) {
        this(id, message, useResponseQueue, timeoutMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * Instantiates a new Request.
     *
     * @param id      the id
     * @param message the message
     * @param timeout the timeout
     * @param unit    the unit
     */
    public Request(Object id, Object message, long timeout, TimeUnit unit) {
        this(id, message, true, timeout, unit);
    }

    /**
     * Instantiates a new Request.
     *
     * @param id               the id
     * @param message          the message
     * @param useResponseQueue the use response queue
     * @param timeout          the timeout
     * @param unit             the unit
     */
    public Request(Object id, Object message, boolean useResponseQueue, long timeout, TimeUnit unit) {
        if (id == null) {
            throw new IllegalArgumentException("id");
        }
        if (message == null) {
            throw new IllegalArgumentException("message");
        }
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout: " + timeout + " (expected: 0+)");
        } else if (timeout == 0) {
            timeout = Long.MAX_VALUE;
        }

        if (unit == null) {
            throw new IllegalArgumentException("unit");
        }

        this.id = id;
        this.message = message;
        this.responses = useResponseQueue ? new LinkedBlockingQueue<Object>() : null;
        this.timeoutMillis = unit.toMillis(timeout);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Object getId() {
        return id;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public Object getMessage() {
        return message;
    }

    /**
     * Gets timeout millis.
     *
     * @return the timeout millis
     */
    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    /**
     * Is use response queue boolean.
     *
     * @return the boolean
     */
    public boolean isUseResponseQueue() {
        return responses != null;
    }

    /**
     * Has response boolean.
     *
     * @return the boolean
     */
    public boolean hasResponse() {
        checkUseResponseQueue();
        return !responses.isEmpty();
    }

    /**
     * Await response response.
     *
     * @return the response
     * @throws RequestTimeoutException the request timeout exception
     * @throws InterruptedException    the interrupted exception
     */
    public Response awaitResponse() throws RequestTimeoutException, InterruptedException {
        checkUseResponseQueue();
        chechEndOfResponses();
        return convertToResponse(responses.take());
    }

    /**
     * Await response response.
     *
     * @param timeout the timeout
     * @param unit    the unit
     * @return the response
     * @throws RequestTimeoutException the request timeout exception
     * @throws InterruptedException    the interrupted exception
     */
    public Response awaitResponse(long timeout, TimeUnit unit) throws RequestTimeoutException, InterruptedException {
        checkUseResponseQueue();
        chechEndOfResponses();
        return convertToResponse(responses.poll(timeout, unit));
    }

    private Response convertToResponse(Object o) {
        if (o instanceof Response) {
            return (Response) o;
        }

        if (o == null) {
            return null;
        }

        throw (RequestTimeoutException) o;
    }

    /**
     * Await response uninterruptibly response.
     *
     * @return the response
     * @throws RequestTimeoutException the request timeout exception
     */
    public Response awaitResponseUninterruptibly() throws RequestTimeoutException {
        for (;;) {
            try {
                return awaitResponse();
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }

    private void chechEndOfResponses() {
        if (responses != null && endOfResponses && responses.isEmpty()) {
            throw new NoSuchElementException("All responses has been retrieved already.");
        }
    }

    private void checkUseResponseQueue() {
        if (responses == null) {
            throw new UnsupportedOperationException("Response queue is not available; useResponseQueue is false.");
        }
    }

    /**
     * Signal.
     *
     * @param response the response
     */
    void signal(Response response) {
        signal0(response);
        if (response.getType() != ResponseType.PARTIAL) {
            endOfResponses = true;
        }
    }

    /**
     * Signal.
     *
     * @param e the e
     */
    void signal(RequestTimeoutException e) {
        signal0(e);
        endOfResponses = true;
    }

    private void signal0(Object answer) {
        if (responses != null) {
            responses.add(answer);
        }
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof Request)) {
            return false;
        }

        Request that = (Request) o;
        return this.getId().equals(that.getId());
    }

    @Override
    public String toString() {
        String timeout = getTimeoutMillis() == Long.MAX_VALUE ? "max" : String.valueOf(getTimeoutMillis());

        return "request: { id=" + getId() + ", timeout=" + timeout + ", message=" + getMessage() + " }";
    }

    /**
     * Gets timeout task.
     *
     * @return the timeout task
     */
    Runnable getTimeoutTask() {
        return timeoutTask;
    }

    /**
     * Sets timeout task.
     *
     * @param timeoutTask the timeout task
     */
    void setTimeoutTask(Runnable timeoutTask) {
        this.timeoutTask = timeoutTask;
    }

    /**
     * Gets timeout future.
     *
     * @return the timeout future
     */
    ScheduledFuture<?> getTimeoutFuture() {
        return timeoutFuture;
    }

    /**
     * Sets timeout future.
     *
     * @param timeoutFuture the timeout future
     */
    void setTimeoutFuture(ScheduledFuture<?> timeoutFuture) {
        this.timeoutFuture = timeoutFuture;
    }
}
