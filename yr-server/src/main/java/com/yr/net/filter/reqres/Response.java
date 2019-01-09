package com.yr.net.filter.reqres;

import com.yr.net.filter.reqres.Request;

/**
 * The type Response.
 */
public class Response {
    private final Request request;


    private final Object message;

    /**
     * Instantiates a new Response.
     *
     * @param request the request
     * @param message the message
     */
    public Response(Request request, Object message) {
        if (request == null) {
            throw new IllegalArgumentException("request");
        }

        if (message == null) {
            throw new IllegalArgumentException("message");
        }


        this.request = request;
        this.message = message;
    }

    /**
     * Gets request.
     *
     * @return the request
     */
    public Request getRequest() {
        return request;
    }


    /**
     * Gets message.
     *
     * @return the message
     */
    public Object getMessage() {
        return message;
    }

    @Override
    public int hashCode() {
        return getRequest().getId().hashCode();
    }


    @Override
    public String toString() {
        return "response: { requestId=" + getRequest().getId() + ", type=" + getType() + ", message=" + getMessage()
                + " }";
    }
}
