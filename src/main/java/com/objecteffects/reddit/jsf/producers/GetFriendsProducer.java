package com.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditGet;
import com.objecteffects.reddit.method.GetFriends;
import com.objecteffects.reddit.method.GetPosts;
import com.objecteffects.reddit.method.UnFriend;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class GetFriendsProducer implements Serializable {
    private static final long serialVersionUID = -1L;

    @Inject
    private transient Logger log;

    @Inject
    private RedditGet getMethod;

    @Inject
    private UnFriend unFriend;

    @Inject
    private GetPosts getPosts;

    /**
     * @return
     */
    @Priority(100)
    @Produces
    @ApplicationScoped
    public GetFriends getFriends() {
        this.log.debug("getFriends");

        final GetFriends gf = new GetFriends();

        gf.setGetMethod(this.getMethod);
        gf.setUnFriend(this.unFriend);
        gf.setGetPosts(this.getPosts);

        return gf;
    }
}
