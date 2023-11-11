package org.objecteffects.reddit.jsf.service;

import com.objecteffects.reddit.method.GetFriends;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class FriendsProducer {
    @Produces
    public GetFriends simpleFriendsProducer() {
        return new GetFriends();
    }

}
