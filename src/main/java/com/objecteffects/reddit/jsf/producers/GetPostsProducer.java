package com.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditGet;
import com.objecteffects.reddit.method.GetPosts;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class GetPostsProducer implements Serializable {
    private static final long serialVersionUID = -1L;

    @Inject
    private transient Logger log;

    @Inject
    private RedditGet redditGet;

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
    public GetPosts getGetPosts() {
        this.log.debug("getGetPosts");

        final GetPosts rgp = new GetPosts();

        rgp.setGet(this.redditGet);

        return rgp;
    }
}
