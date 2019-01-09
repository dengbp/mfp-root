package com.yr.net.filterchain;

/**
 * The interface Io filter.
 */
public interface IoFilter {
    /**
     * Invoked by {@link ReferenceCountingFilter} when this filter
     * is added to a {@link IoFilterChain} at the first time, so you can
     * initialize shared resources.  Please note that this method is never
     * called if you don't wrap a filter with {@link ReferenceCountingFilter}.
     *
     * @throws Exception the exception
     */
    void init() throws Exception;

    /**
     * Invoked by {@link ReferenceCountingFilter} when this filter
     * is not used by any {@link IoFilterChain} anymore, so you can destroy
     * shared resources.  Please note that this method is never called if
     * you don't wrap a filter with {@link ReferenceCountingFilter}.
     *
     * @throws Exception the exception
     */
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

    /**
     * Filters {@link IoHandler#sessionCreated(IoSession)} event.
     *
     * @param nextFilter the next filter
     * @param session    the session
     * @throws Exception the exception
     */
    void sessionCreated(NextFilter nextFilter, IoSession session) throws Exception;

    /**
     * Filters {@link IoHandler#sessionOpened(IoSession)} event.
     *
     * @param nextFilter the next filter
     * @param session    the session
     * @throws Exception the exception
     */
    void sessionOpened(NextFilter nextFilter, IoSession session) throws Exception;

    /**
     * Filters {@link IoHandler#sessionClosed(IoSession)} event.
     *
     * @param nextFilter the next filter
     * @param session    the session
     * @throws Exception the exception
     */
    void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception;

    /**
     * Filters {@link IoHandler#sessionIdle(IoSession, IdleStatus)}
     * event.
     *
     * @param nextFilter the next filter
     * @param session    the session
     * @param status     the status
     * @throws Exception the exception
     */
    void sessionIdle(NextFilter nextFilter, IoSession session, IdleStatus status) throws Exception;

    /**
     * Filters {@link IoHandler#exceptionCaught(IoSession, Throwable)}
     * event.
     *
     * @param nextFilter the next filter
     * @param session    the session
     * @param cause      the cause
     * @throws Exception the exception
     */
    void exceptionCaught(NextFilter nextFilter, IoSession session, Throwable cause) throws Exception;

    /**
     * Filters {@link IoHandler#messageReceived(IoSession, Object)}
     * event.
     *
     * @param nextFilter the next filter
     * @param session    the session
     * @param message    the message
     * @throws Exception the exception
     */
    void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception;

    /**
     * Filters {@link IoHandler#messageSent(IoSession, Object)}
     * event.
     *
     * @param nextFilter   the next filter
     * @param session      the session
     * @param writeRequest the write request
     * @throws Exception the exception
     */
    void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception;

    /**
     * Filters {@link IoSession#close()} method invocation.
     *
     * @param nextFilter the next filter
     * @param session    the session
     * @throws Exception the exception
     */
    void filterClose(NextFilter nextFilter, IoSession session) throws Exception;

    /**
     * Filters {@link IoSession#write(Object)} method invocation.
     *
     * @param nextFilter   the next filter
     * @param session      the session
     * @param writeRequest the write request
     * @throws Exception the exception
     */
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
         * Forwards <tt>sessionIdle</tt> event to next filter.
         *
         * @param session the session
         * @param status  the status
         */
        void sessionIdle(IoSession session, IdleStatus status);

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
