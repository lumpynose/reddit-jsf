package org.objecteffects.reddit.jsf.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;

import com.objecteffects.reddit.data.Friend;
import com.objecteffects.reddit.method.GetFriends;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 *
 */
@Stateless
public class FriendsService implements Serializable {
    private static final long serialVersionUID = -605449122067756284L;

    @Inject
    @SuppressWarnings("unused")
    private transient Logger log;

    @Inject
    GetFriends getFriends;

    /**
     * @return List of Friend
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Friend> getFriends()
            throws IOException, InterruptedException {
        return this.getFriends.getFriends();
    }
}
