package org.objecteffects.reddit.jsf.view;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 */
@Named
@ApplicationScoped
public class Result implements Serializable {
    private static final long serialVersionUID = -4950508763640509054L;

    @Inject
    private transient Logger log;

    @SuppressWarnings("boxing")
    private Integer counter = 0;

//    @Inject
//    @ManagedProperty("#{flash.result}")
    @SuppressWarnings("unchecked")
    private final Future<String> result =
            (Future<String>) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash()
                    .get("result");

    /**
     */
    @PostConstruct
    public void init() {
        this.log.debug("init");
    }

    /**
     * @return
     */
    public boolean getReady() {
        return this.result.isDone();
    }

    /**
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public String getResult()
            throws InterruptedException, ExecutionException {
        if (this.result.isDone()) {
            return this.result.get();
        }

        return "";
    }

    /**
     */
    public void cancel() {
        this.result.cancel(true);
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
