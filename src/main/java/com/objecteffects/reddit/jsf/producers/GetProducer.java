package com.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditGet;
import com.objecteffects.reddit.core.RedditHttpClient;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class GetProducer implements Serializable {
    private static final long serialVersionUID = -1L;

    @Inject
    private transient Logger log;

    @Inject
    private RedditHttpClient redditHttpClient;

//    /**
//     * @param _redditHttpClient the redditHttpClient to set
//     */
//    public void setRedditHttpClient(final RedditHttpClient _redditHttpClient) {
//        this.redditHttpClient = _redditHttpClient;
//    }

    /**
     * @return
     */
    @Priority(100)
    @Produces
    @ApplicationScoped
    public RedditGet getGetMethod() {
        this.log.debug("getGetMethod");

        final RedditGet rgm = new RedditGet();

        rgm.setRedditHttpClient(this.redditHttpClient);

        return rgm;
    }
}
