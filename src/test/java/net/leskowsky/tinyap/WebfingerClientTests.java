package net.leskowsky.tinyap;

import org.junit.jupiter.api.Test;

public class WebfingerClientTests {

    // get account
    @Test
    void getAccount() {
        // Given a username and domain
        // When I sent a webfinger request
        // I should get back an account resource with subject acct:user@domain
    }

    // handle account doesn't exist
    // handle server error
    // handle multiple lookups required to find account
    // add generic error handler
}
