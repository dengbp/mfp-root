package com.yr.net.filterchain;

import com.yr.net.session.IoSession;
import com.yr.net.write.WriteRequest;

/**
 * The interface Io filter.
 */
public interface IoFilter {
    void init() throws Exception;

    void destroy() throws Exception;

    /**
     * Invoked before this filter is added to the specified <tt>parent</tt>.
     * Please note that this method can be invoked more than once if
     * this filter is added to more than one parents.  This method is not
     * invoked before {@link #init()} is invoked.
     *
     * @param parent     the parent who called this method
     * @param name       the name assigned to this filter
     * @param nextFilter the {@link NextFilter} for this filter.  You can reuse                   this object until this filter is removed from the chain.
     * @throws Exception the exception
     */
    void onPreAdd(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception;

    /**
     * Invoked after this filter is added to the specified <tt>parent</tt>.
     * Please note that this method can be invoked more than once if
     * this filter is added to more than one parents.  This method is not
     * invoked before {@link #init()} is invoked.
     *
     * @param parent     the parent who called this method
     * @param name       the name assigned to this filter
     * @param nextFilter the {@link NextFilter} for this filter.  You can reuse                   this object until this filter is removed from the chain.
     * @throws Exception the exception
     */
    void onPostAdd(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception;

    /**
     * Invoked before this filter is removed from the specified <tt>parent</tt>.
     * Please note that this method can be invoked more than once if
     * this filter is removed from more than one parents.
     * This method is always invoked before {@link #destroy()} is invoked.
     *
     * @param parent     the parent who called this method
     * @param name       the name assigned to this filter
     * @param nextFilter the {@link NextFilter} for this filter.  You can reuse                   this object until this filter is removed from the chain.
     * @throws Exception the exception
     */
    void onPreRemove(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception;

    /**
     * Invoked after this filter is removed from the specified <tt>parent</tt>.
     * Please note that this method can be invoked more than once if
     * this filter is removed from more than one parents.
     * This method is always invoked before {@link #destroy()} is invoked.
     *
     * @param parent     the parent who called this method
     * @param name       the name assigned to this filter
     * @param nextFilter the {@link NextFilter} for this filter.  You can reuse                   this object until this filter is removed from the chain.
     * @throws Exception the exception
     */
    void onPostRemove(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception;

    void sessionCreated(NextFilter nextFilter, IoSession session) throws Exception;

    void sessionOpened(NextFilter nextFilter, IoSession session) throws Exception;

    void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception;


    void exceptionCaught(NextFilter nextFilter, IoSession session, Throwable cause) throws Exception;
    void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception;

    void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception;

    void filterClose(NextFilter nextFilter, IoSession session) throws Exception;

    void filterWrite(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception;

    /**
     * Represents the next {@link IoFilter} in {@link IoFilterChain}.
     */
    public interface NextFilter {
        /**
         * Forwards <tt>sessionCreated</tt> event to next filter.
         *
         * @param session the session
         */
        void sessionCreated(IoSession session);

        /**
         * Forwards <tt>sessionOpened</tt> event to next filter.
         *
         * @param session the session
         */
        void sessionOpened(IoSession session);

        /**
         * Forwards <tt>sessionClosed</tt> event to next filter.
         *
         * @param session the session
         */
        void sessionClosed(IoSession session);


        /**
         * Forwards <tt>exceptionCaught</tt> event to next filter.
         *
         * @param session the session
         * @param cause   the cause
         */
        void exceptionCaught(IoSession session, Throwable cause);

        /**
         * Forwards <tt>messageReceived</tt> event to next filter.
         *
         * @param session the session
         * @param message the message
         */
        void messageReceived(IoSession session, Object message);

        /**
         * Forwards <tt>messageSent</tt> event to next filter.
         *
         * @param session      the session
         * @param writeRequest the write request
         */
        void messageSent(IoSession session, WriteRequest writeRequest);

        /**
         * Forwards <tt>filterWrite</tt> event to next filter.
         *
         * @param session      the session
         * @param writeRequest the write request
         */
        void filterWrite(IoSession session, WriteRequest writeRequest);

        /**
         * Forwards <tt>filterClose</tt> event to next filter.
         *
         * @param session the session
         */
        void filterClose(IoSession session);

    }
}
