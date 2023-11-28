package com.objecteffects.reddit.jsf.view;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;

import com.objecteffects.reddit.data.Friend;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 */
@Named
@ApplicationScoped
public class ResultFriends implements Serializable {
    private static final long serialVersionUID = -4950508763640509054L;

    @Inject
    private transient Logger log;

    @SuppressWarnings("boxing")
    private Integer counter = 0;

//    @Inject
//    @ManagedProperty("#{flash.result}")
//    private final Future<List<Friend>> result;
    @SuppressWarnings("unchecked")
    private final Future<List<Friend>> result =
            (Future<List<Friend>>) FacesContext.getCurrentInstance()
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
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public List<Friend> getResult()
            throws InterruptedException, ExecutionException {
        if (this.result.isDone()) {
            final List<Friend> friends = this.result.get();

            this.log.debug("result: {}", this.result.get());

            Collections.sort(friends, Collections.reverseOrder());

            return friends;
        }
        // else

        return Collections.emptyList();
    }

    /**
     * @return
     */
    public boolean getReady() {
        return this.result.isDone();
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
     * @param _counter the counter to set
     */
    public void setCounter(final Integer _counter) {
        this.counter = _counter;
    }
}
