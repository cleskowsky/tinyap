package net.leskowsky.tinyap.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class FollowServiceTests {

    // Get activitypub url for actor (webfinger)
    @Test
    void getWebfingerResource() {
        // Given a user and domain of an actor on mastodon,
        // When I make a webfinger request,
        // Then I get back a response with a fediverse user url
    }


    // a webfinger subject may be different from the searched for user@domain
    // request follow
    // accept request
    // deny request
    // handle actor not found
    // follow actor
}
