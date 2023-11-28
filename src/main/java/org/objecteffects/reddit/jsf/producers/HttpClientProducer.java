package org.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditHttpClient;
import com.objecteffects.reddit.core.RedditOAuth;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class HttpClientProducer implements Serializable {
    private static final long serialVersionUID = -1L;

    @Inject
    private transient Logger log;

    @Inject
    private RedditOAuth oauth;

    /**
     * @return
     */
    @Priority(100)
    @Produces
    @ApplicationScoped
    public RedditHttpClient getHttpClient() {
        this.log.debug("getHttpClient");

        final RedditHttpClient hc = new RedditHttpClient();

        hc.setRedditOAuth(this.oauth);

        return hc;
    }
}
