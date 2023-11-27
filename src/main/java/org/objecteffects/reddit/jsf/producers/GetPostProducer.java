package org.objecteffects.reddit.jsf.producers;

import java.io.Serializable;

import org.slf4j.Logger;

import com.objecteffects.reddit.core.RedditPostMethod;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 */
@ApplicationScoped
public class GetPostProducer implements Serializable {
    private static final long serialVersionUID = 4313581015685920911L;

    @Inject
    private transient Logger log;

    /**
     * @return
     */
    @Priority(100)
    @Produces
    @ApplicationScoped
    public RedditPostMethod getPostMethod() {
        this.log.debug("getPostMethod");

        return new RedditPostMethod();
    }
}
