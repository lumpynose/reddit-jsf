package org.objecteffects.reddit.jsf.producers;

import java.io.IOException;
import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditOAuth;
import com.objecteffects.reddit.main.AppConfig;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class OAuthProducer implements Serializable {
    private static final long serialVersionUID = -1L;

    @Inject
    private transient Logger log;

    @Inject
    private AppConfig appConfig;

    /**
     * @return
     */
    @Priority(100)
    @Produces
    @ApplicationScoped
    public RedditOAuth getOAuth() {
        this.log.debug("getOAuth");

        final RedditOAuth oauth = new RedditOAuth();
        oauth.setAppConfig(this.appConfig);

        return oauth;
    }

    void revoke(@Disposes final RedditOAuth redditOAuth)
            throws IOException, InterruptedException {
        this.log.debug("revoking token");

        redditOAuth.revokeToken();
    }
}
