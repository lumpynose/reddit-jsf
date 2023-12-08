package com.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditHttpClient;
import com.objecteffects.reddit.core.RedditPost;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class PostProducer implements Serializable {
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
    public RedditPost getPostMethod() {
        this.log.debug("getPostMethod");

        final RedditPost rpm = new RedditPost();

        rpm.setRedditHttpClient(this.redditHttpClient);

        return rpm;
    }
}
