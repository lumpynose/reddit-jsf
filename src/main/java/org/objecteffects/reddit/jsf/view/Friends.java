package org.objecteffects.reddit.jsf.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import org.objecteffects.reddit.jsf.service.FriendsService;
import org.slf4j.Logger;

import com.objecteffects.reddit.data.Friend;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Friends implements Serializable {
    private static final long serialVersionUID = -570500230181100578L;

    @Inject
    private transient Logger log;

    @Inject
    private FriendsService friendsService;

    @PostConstruct
    public void init() {
        this.log.debug("init");
    }

    /**
     * @return List of Friend
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Friend> getFriends() throws IOException, InterruptedException {
        this.log.debug("get friends");

        final List<Friend> friends = this.friendsService.getFriends();

        friends.sort(Comparator.naturalOrder());

        return friends;
    }
}
