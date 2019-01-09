package com.yr.net.filterchain;

import java.util.List;

import org.apache.mina.core.filterchain.IoFilter.NextFilter;


/**
 * The interface Io filter chain.
 */
public interface IoFilterChain {
    /**
     * Returns the parent {@link IoSession} of this chain.
     *
     * @return {@link IoSession}
     */
    IoSession getSession();


    /**
     * Get io filter.
     *
     * @param name the name
     * @return the io filter
     */
    IoFilter get(String name);

    /**
     * Returns the {@link IoFilter} with the specified <tt>filterType</tt>
     * in this chain. If there's more than one filter with the specified
     * type, the first match will be chosen.
     *
     * @param filterType The filter class
     * @return <tt>null</tt> if there's no such name in this chain
     */
    IoFilter get(Class<? extends IoFilter> filterType);

    /**
     * Returns the {@link NextFilter} of the {@link IoFilter} with the
     * specified <tt>name</tt> in this chain.
     *
     * @param name The filter's name we want the next filter
     * @return <tt>null</tt> if there's no such name in this chain
     */
    NextFilter getNextFilter(String name);

    /**
     * Returns the {@link NextFilter} of the specified {@link IoFilter}
     * in this chain.
     *
     * @param filter The filter for which we want the next filter
     * @return <tt>null</tt> if there's no such name in this chain
     */
    NextFilter getNextFilter(IoFilter filter);

    /**
     * Returns the {@link NextFilter} of the specified <tt>filterType</tt>
     * in this chain.  If there's more than one filter with the specified
     * type, the first match will be chosen.
     *
     * @param filterType The Filter class for which we want the next filter
     * @return <tt>null</tt> if there's no such name in this chain
     */
    NextFilter getNextFilter(Class<? extends IoFilter> filterType);


    /**
     * Contains boolean.
     *
     * @param name The filter's name we are looking for
     * @return <tt>true</tt> if this chain contains an {@link IoFilter} with the specified <tt>name</tt>.
     */
    boolean contains(String name);

    /**
     * Contains boolean.
     *
     * @param filter The filter we are looking for
     * @return <tt>true</tt> if this chain contains the specified <tt>filter</tt>.
     */
    boolean contains(IoFilter filter);

    /**
     * Contains boolean.
     *
     * @param filterType The filter's class we are looking for
     * @return <tt>true</tt> if this chain contains an {@link IoFilter} of the specified <tt>filterType</tt>.
     */
    boolean contains(Class<? extends IoFilter> filterType);

    /**
     * Adds the specified filter with the specified name at the beginning of this chain.
     *
     * @param name   The filter's name
     * @param filter The filter to add
     */
    void addFirst(String name, IoFilter filter);

    /**
     * Adds the specified filter with the specified name at the end of this chain.
     *
     * @param name   The filter's name
     * @param filter The filter to add
     */
    void addLast(String name, IoFilter filter);

    /**
     * Adds the specified filter with the specified name just before the filter whose name is
     * <code>baseName</code> in this chain.
     *
     * @param baseName The targeted Filter's name
     * @param name     The filter's name
     * @param filter   The filter to add
     */
    void addBefore(String baseName, String name, IoFilter filter);

    /**
     * Adds the specified filter with the specified name just after the filter whose name is
     * <code>baseName</code> in this chain.
     *
     * @param baseName The targeted Filter's name
     * @param name     The filter's name
     * @param filter   The filter to add
     */
    void addAfter(String baseName, String name, IoFilter filter);

    /**
     * Replace the filter with the specified name with the specified new
     * filter.
     *
     * @param name      The name of the filter we want to replace
     * @param newFilter The new filter
     * @return the old filter
     */
    IoFilter replace(String name, IoFilter newFilter);

    /**
     * Replace the filter with the specified name with the specified new
     * filter.
     *
     * @param oldFilter The filter we want to replace
     * @param newFilter The new filter
     */
    void replace(IoFilter oldFilter, IoFilter newFilter);

    /**
     * Replace the filter of the specified type with the specified new
     * filter.  If there's more than one filter with the specified type,
     * the first match will be replaced.
     *
     * @param oldFilterType The filter class we want to replace
     * @param newFilter     The new filter
     * @return the io filter
     */
    IoFilter replace(Class<? extends IoFilter> oldFilterType, IoFilter newFilter);

    /**
     * Removes the filter with the specified name from this chain.
     *
     * @param name The name of the filter to remove
     * @return The removed filter
     */
    IoFilter remove(String name);

    /**
     * Replace the filter with the specified name with the specified new
     * filter.
     *
     * @param filter the filter
     */
    void remove(IoFilter filter);

    /**
     * Replace the filter of the specified type with the specified new
     * filter.  If there's more than one filter with the specified type,
     * the first match will be replaced.
     *
     * @param filterType the filter type
     * @return The removed filter
     */
    IoFilter remove(Class<? extends IoFilter> filterType);

    /**
     * Removes all filters added to this chain.
     *
     * @throws Exception the exception
     */
    void clear() throws Exception;

    /**
     * Fires a {@link IoHandler#sessionCreated(IoSession)} event. Most users don't need to
     * call this method at all. Please use this method only when you implement a new transport
     * or fire a virtual event.
     */
    public void fireSessionCreated();

    /**
     * Fires a {@link IoHandler#sessionOpened(IoSession)} event. Most users don't need to call
     * this method at all. Please use this method only when you implement a new transport or
     * fire a virtual event.
     */
    public void fireSessionOpened();

    /**
     * Fires a {@link IoHandler#sessionClosed(IoSession)} event. Most users don't need to call
     * this method at all. Please use this method only when you implement a new transport or
     * fire a virtual event.
     */
    public void fireSessionClosed();

    /**
     * Fires a {@link IoHandler#sessionIdle(IoSession, IdleStatus)} event. Most users don't
     * need to call this method at all. Please use this method only when you implement a new
     * transport or fire a virtual event.
     *
     * @param status The current status to propagate
     */
    public void fireSessionIdle(IdleStatus status);


    /**
     * Fires a {@link IoSession#write(Object)} event. Most users don't need to call this
     * method at all. Please use this method only when you implement a new transport or fire a
     * virtual event.
     *
     * @param writeRequest The message to write
     */
    public void fireFilterWrite(WriteRequest writeRequest);

    /**
     * Fires a {@link IoSession#close()} event. Most users don't need to call this method at
     * all. Please use this method only when you implement a new transport or fire a virtual
     * event.
     */
    public void fireFilterClose();


}
