package com.yr.net.filterchain;

/**
 * The interface Io filter chain builder.
 */
public interface IoFilterChainBuilder {
    /**
     * An implementation which does nothing.
     */
    IoFilterChainBuilder NOOP = new IoFilterChainBuilder() {
        public void buildFilterChain(IoFilterChain chain) throws Exception {
        }

        @Override
        public String toString() {
            return "NOOP";
        }
    };

    /**
     * Modifies the specified <tt>chain</tt>.
     *
     * @param chain the chain
     * @throws Exception the exception
     */
    void buildFilterChain(IoFilterChain chain) throws Exception;
}
