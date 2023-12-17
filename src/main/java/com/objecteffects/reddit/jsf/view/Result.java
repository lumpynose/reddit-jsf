package com.objecteffects.reddit.jsf.view;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;

import com.objecteffects.reddit.jsf.service.FuturesQueue;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 */
@Named
@RequestScoped
public class Result implements Serializable {
    private static final long serialVersionUID = -1L;

    @Inject
    private transient Logger log;

    @SuppressWarnings("boxing")
    private Integer counter = 0;

    @Inject
    private FuturesQueue futuresQueue;

//    @Inject
//    @ManagedProperty("#{flash.future}")
    private Future<String> future;

    /**
     */
    @PostConstruct
    public void init() {
        this.log.debug("init");

//        this.future = (Future<String>) FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getFlash()
//                .get("future");

        this.future = this.futuresQueue.get();

        this.log.debug("future: {}", this.future);
    }

    /**
     */
    public void cancel() {
        this.future.cancel(true);
    }

    /**
     * @return
     */
    public boolean getReady() {
        return this.future.isDone();
    }

    /**
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public String getFuture() throws InterruptedException, ExecutionException {
        if (this.future.isDone()) {
            return this.future.get();
        }

        return null;
    }

    /**
     * @return the counter
     */
    @SuppressWarnings("boxing")
    public Integer getCounter() {
        this.counter = this.counter + 1;

        return this.counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(final Integer _counter) {
        this.counter = _counter;
    }
}
