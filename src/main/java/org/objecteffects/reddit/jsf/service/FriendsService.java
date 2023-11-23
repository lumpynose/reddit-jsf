package org.objecteffects.reddit.jsf.service;

import java.io.Serializable;

import org.slf4j.Logger;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/**
 *
 */
@Stateless
public class FriendsService implements Serializable {
    private static final long serialVersionUID = -605449122067756284L;

    @Inject
    private transient Logger log;

//    @Inject
//    GetFriends getFriends;
//
//    /**
//     * @return List of Friend
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public List<Friend> getFriends()
//            throws IOException, InterruptedException {
//        this.log.debug("getFriends");
//
//        return this.getFriends.getFriends();
//    }
}
