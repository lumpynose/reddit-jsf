package com.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditDeleteMethod;
import com.objecteffects.reddit.method.UnFriend;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class UnFriendProducer implements Serializable {
    private static final long serialVersionUID = -1L;

    @Inject
    private transient Logger log;

    @Inject
    private RedditDeleteMethod deleteMethod;

    /**
     * @return
     */
    @Priority(100)
    @Produces
    @ApplicationScoped
    public UnFriend getUnFriend() {
        this.log.debug("getUnfriend");

        final UnFriend rpm = new UnFriend();

        rpm.setDeleteMethod(this.deleteMethod);

        return rpm;
    }
}
