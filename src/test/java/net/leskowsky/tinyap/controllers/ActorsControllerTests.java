package net.leskowsky.tinyap.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ActorsController.class)
public class ActorsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void actorSearch() throws Exception {
        mockMvc.perform(get("/actors/search?q=@fraggle@mastodon.gamedev.place"))
                .andExpect(content().string(containsString("Found @fraggle@mastodon.gamedev.place")));
    }
}
