package com.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditPost;
import com.objecteffects.reddit.method.GetPosts;
import com.objecteffects.reddit.method.UpVotePosts;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class UpVotePostsProducer implements Serializable {
    private static final long serialVersionUID = -1L;

    @Inject
    private transient Logger log;

    @Inject
    private GetPosts rgp;

    @Inject
    private RedditPost rpm;

    /**
     * @return UpVotePosts
     */
    @Priority(100)
    @Produces
    @ApplicationScoped
    public UpVotePosts getUpVotePosts() {
        this.log.debug("getUpVotePosts");

        final UpVotePosts uvp = new UpVotePosts();

        uvp.setGetPosts(this.rgp);
        uvp.setPostClient(this.rpm);

        return uvp;
    }
}
