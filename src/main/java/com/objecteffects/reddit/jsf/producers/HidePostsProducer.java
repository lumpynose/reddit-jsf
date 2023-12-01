package com.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditGetMethod;
import com.objecteffects.reddit.core.RedditPostMethod;
import com.objecteffects.reddit.method.HidePosts;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class HidePostsProducer implements Serializable {
    private static final long serialVersionUID = -1L;

    @Inject
    private transient Logger log;

    @Inject
    private RedditGetMethod rgm;

    @Inject
    private RedditPostMethod rpm;

    /**
     * @return
     */
    @Priority(100)
    @Produces
    @ApplicationScoped
    public HidePosts getHidePosts() {
        this.log.debug("getHidePosts");

        final HidePosts hp = new HidePosts();

        hp.setGetClient(this.rgm);
        hp.setPostClient(this.rpm);

        return hp;
    }
}
