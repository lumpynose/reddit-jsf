package org.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditGetMethod;
import com.objecteffects.reddit.core.RedditPostMethod;
import com.objecteffects.reddit.method.UpVotePosts;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class UpVotePostsProducer implements Serializable {
    private static final long serialVersionUID = 4313581015685920911L;

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
    public UpVotePosts getUpVotePosts() {
        this.log.debug("getUpVotePosts");

        final UpVotePosts uvp = new UpVotePosts();

        uvp.setGetClient(this.rgm);
        uvp.setPostClient(this.rpm);

        return uvp;
    }
}
