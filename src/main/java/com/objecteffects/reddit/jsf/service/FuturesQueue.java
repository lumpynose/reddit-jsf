package com.objecteffects.reddit.jsf.service;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

import jakarta.enterprise.context.SessionScoped;

/**
 */
@SessionScoped
public class FuturesQueue implements Serializable {
    private static final long serialVersionUID = 1L;

    private static List<Future<String>> list =
            Collections.synchronizedList(new LinkedList<Future<String>>());

    /**
     * @param future
     */
    public void add(final Future<String> future) {
        this.list.add(future);
    }

    /**
     * @return
     */
    public Future<String> get() {
        return this.list.get(this.list.size() - 1);
    }

    public void remove() {
        list.remove(0);
    }
}
