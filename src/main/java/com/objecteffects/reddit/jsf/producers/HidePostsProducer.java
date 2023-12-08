package com.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditPost;
import com.objecteffects.reddit.method.GetPosts;
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
    private GetPosts rgm;

    @Inject
    private RedditPost rpm;

    /**
     * @return
     */
    @Priority(100)
    @Produces
    @ApplicationScoped
    public HidePosts getHidePosts() {
        this.log.debug("getHidePosts");

        final HidePosts hp = new HidePosts();

        hp.setGetPosts(this.rgm);
        hp.setPost(this.rpm);

        return hp;
    }
}
