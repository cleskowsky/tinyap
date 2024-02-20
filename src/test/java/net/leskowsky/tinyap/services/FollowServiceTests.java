package net.leskowsky.tinyap.services;

public class FollowServiceTests {

    // follow actor
//    @Test
//    void sendFollowRequest() {
//        // Given a user and domain of an actor in the fediverse,
//        String user = "cleskowsky";
//        String domain = "mastodon.social";
//
//        // When I make a webfinger request for the user@domain,
//        FollowService followService = new FollowService();
//        FollowRequest followRequest = followService.sendFollowRequest(user, domain);
//
//        // I get back a response containing an actor url
//        assertEquals(followRequest.getStatus(), FollowRequestStatus.Waiting);
//    }


    // a webfinger subject may be different from the searched for user@domain
    // request follow
    // accept request
    // deny request
    // handle actor not found
    // handle server timed out or returns 500
}
