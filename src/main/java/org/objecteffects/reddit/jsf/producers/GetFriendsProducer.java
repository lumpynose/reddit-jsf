package org.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.method.GetFriends;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 *
 */
@ApplicationScoped
public class GetFriendsProducer implements Serializable {
    private static final long serialVersionUID = 4313581015685920911L;

    @Inject
    private transient Logger log;

    /**
     * @return
     */
    @Priority(100)
    @Produces
    @ApplicationScoped
    public GetFriends getFriends() {
        this.log.debug("getFriends");

        return new GetFriends();
    }
}
