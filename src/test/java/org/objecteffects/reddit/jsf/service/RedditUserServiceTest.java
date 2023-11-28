package org.objecteffects.reddit.jsf.service;

import java.util.List;

import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import com.objecteffects.reddit.jsf.logging.LoggerProducer;
import com.objecteffects.reddit.jsf.model.RedditUser;

import jakarta.inject.Inject;

/**
 *
 */
@EnableWeld
public class RedditUserServiceTest {
    @Inject
    private transient Logger log;

    @WeldSetup
    public WeldInitiator weld =
            WeldInitiator.of(UserService.class, LoggerProducer.class);

    @Inject
    private UserService redditUserService;

    @Test
    public void testGetRedditUsers() {
        final List<RedditUser> users = this.redditUserService
                .getUsers();

        for (final RedditUser user : users) {
            this.log.debug(user.getName());
        }
    }
}
