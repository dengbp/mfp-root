package com.yr.net.filter.executor;

import com.yr.net.filterchain.IoFilterAdapter;

import java.util.concurrent.Executor;

/**
 * The type Executor filter.
 */
public class ExecutorFilter extends IoFilterAdapter {

    /** The associated executor */
    private Executor executor;

    /** A flag set if the executor can be managed */
    private boolean manageableExecutor;

    /** The default pool size */
    private static final int DEFAULT_MAX_POOL_SIZE = 16;

    /** The number of thread to create at startup */
    private static final int BASE_THREAD_NUMBER = 0;

    /** The default KeepAlive time, in seconds */
    private static final long DEFAULT_KEEPALIVE_TIME = 30;


}
