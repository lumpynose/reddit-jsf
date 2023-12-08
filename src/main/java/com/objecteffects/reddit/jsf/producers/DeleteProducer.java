package com.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditDelete;
import com.objecteffects.reddit.core.RedditHttpClient;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class DeleteProducer implements Serializable {
    private static final long serialVersionUID = -1L;

    @Inject
    private transient Logger log;

    @Inject
    private RedditHttpClient redditHttpClient;

    /**
     * @return
     */
    @Priority(100)
    @Produces
    @ApplicationScoped
    public RedditDelete getDeleteMethod() {
        this.log.debug("getDeleteMethod");

        final RedditDelete rpm = new RedditDelete();

        rpm.setRedditHttpClient(this.redditHttpClient);

        return rpm;
    }
}
