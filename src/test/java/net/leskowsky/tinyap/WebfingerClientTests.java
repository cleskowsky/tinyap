package net.leskowsky.tinyap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebfingerClientTests {

    @Test
    void getAccount() {
        // Given a username and domain
        String user = "cleskowsky";
        String domain = "mastodon.social";

        // When I sent a webfinger request
        WebfingerClient client = new WebfingerClient();
        WebfingerAccount account = client.getAccount(user, domain);

        // I should get back an account resource with subject acct:user@domain
        assertEquals("acct:cleskowsky@mastodon.social", account.subject());
        assertEquals("https://mastodon.social/users/cleskowsky", account.actorUrl());
    }

    // handle account doesn't exist
    // handle server error
    // handle multiple lookups required to find account
    // add generic error handler
}
