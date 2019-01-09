package com.yr.net.filterchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.util.AttributeKey;
import org.apache.mina.core.filterchain.IoFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Default io filter chain.
 */
public class DefaultIoFilterChain implements IoFilterChain {

    public IoSession getSession() {
        return null;
    }

    public IoFilter get(String name) {
        return null;
    }

    public IoFilter get(Class<? extends IoFilter> filterType) {
        return null;
    }

    public IoFilter.NextFilter getNextFilter(String name) {
        return null;
    }

    public boolean contains(String name) {
        return false;
    }

    public IoFilter remove(String name) {
        return null;
    }

    public void clear() throws Exception {

    }

    public void fireSessionCreated() {

    }

    public void fireSessionOpened() {

    }

    public void fireSessionClosed() {

    }

    public void fireSessionIdle(IdleStatus status) {

    }

    public void fireFilterWrite(WriteRequest writeRequest) {

    }

    public void fireFilterClose() {

    }

    public IoFilter remove(Class<? extends IoFilter> filterType) {
        return null;
    }

    public void remove(IoFilter filter) {

    }

    public IoFilter replace(Class<? extends IoFilter> oldFilterType, IoFilter newFilter) {
        return null;
    }

    public void replace(IoFilter oldFilter, IoFilter newFilter) {

    }

    public IoFilter replace(String name, IoFilter newFilter) {
        return null;
    }

    public void addAfter(String baseName, String name, IoFilter filter) {

    }

    public void addBefore(String baseName, String name, IoFilter filter) {

    }

    public void addLast(String name, IoFilter filter) {

    }

    public void addFirst(String name, IoFilter filter) {

    }

    public boolean contains(Class<? extends IoFilter> filterType) {
        return false;
    }

    public boolean contains(IoFilter filter) {
        return false;
    }

    public IoFilter.NextFilter getNextFilter(Class<? extends IoFilter> filterType) {
        return null;
    }

    public IoFilter.NextFilter getNextFilter(IoFilter filter) {
        return null;
    }
}
