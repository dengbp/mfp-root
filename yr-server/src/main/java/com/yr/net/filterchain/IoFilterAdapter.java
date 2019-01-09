package com.yr.net.filterchain;

import com.yr.net.filterchain.IoFilter;

/**
 * The type Io filter adapter.
 */
public class IoFilterAdapter implements IoFilter {
    /**
     * {@inheritDoc}
     */
    public void init() throws Exception {
    }

    /**
     * {@inheritDoc}
     */
    public void destroy() throws Exception {
    }

    /**
     * {@inheritDoc}
     */
    public void onPreAdd(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
    }

    /**
     * {@inheritDoc}
     */
    public void onPostAdd(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
    }

    /**
     * {@inheritDoc}
     */
    public void onPreRemove(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
    }

    /**
     * {@inheritDoc}
     */
    public void onPostRemove(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
    }

    /**
     * {@inheritDoc}
     */
    public void sessionCreated(NextFilter nextFilter, IoSession session) throws Exception {
        nextFilter.sessionCreated(session);
    }

    /**
     * {@inheritDoc}
     */
    public void sessionOpened(NextFilter nextFilter, IoSession session) throws Exception {
        nextFilter.sessionOpened(session);
    }

    /**
     * {@inheritDoc}
     */
    public void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception {
        nextFilter.sessionClosed(session);
    }

    /**
     * {@inheritDoc}
     */
    public void sessionIdle(NextFilter nextFilter, IoSession session, IdleStatus status) throws Exception {
        nextFilter.sessionIdle(session, status);
    }

    /**
     * {@inheritDoc}
     */
    public void exceptionCaught(NextFilter nextFilter, IoSession session, Throwable cause) throws Exception {
        nextFilter.exceptionCaught(session, cause);
    }

    /**
     * {@inheritDoc}
     */
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        nextFilter.messageReceived(session, message);
    }

    /**
     * {@inheritDoc}
     */
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        nextFilter.messageSent(session, writeRequest);
    }

    /**
     * {@inheritDoc}
     */
    public void filterWrite(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        nextFilter.filterWrite(session, writeRequest);
    }

    /**
     * {@inheritDoc}
     */
    public void filterClose(NextFilter nextFilter, IoSession session) throws Exception {
        nextFilter.filterClose(session);
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}