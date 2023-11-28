package com.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditGetMethod;
import com.objecteffects.reddit.core.RedditHttpClient;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class GetMethodProducer implements Serializable {
    private static final long serialVersionUID = -1L;

    @Inject
    private transient Logger log;

    @Inject
    private RedditHttpClient redditHttpClient;

    /**
     * @param redditHttpClient the redditHttpClient to set
     */
    public void setRedditHttpClient(final RedditHttpClient redditHttpClient) {
        this.redditHttpClient = redditHttpClient;
    }

    /**
     * @return
     */
    @Priority(100)
    @Produces
    @ApplicationScoped
    public RedditGetMethod getGetMethod() {
        this.log.debug("getGetMethod");

        final RedditGetMethod rgm = new RedditGetMethod();

        rgm.setRedditHttpClient(this.redditHttpClient);

        return rgm;
    }
}
