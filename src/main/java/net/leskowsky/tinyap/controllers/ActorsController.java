package net.leskowsky.tinyap.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/actors")
public class ActorsController {

    private final Logger logger = LoggerFactory.getLogger(ActorsController.class);

    @GetMapping("/search")
    public String search(@RequestParam("q") String q) {
        logger.info("Actor id: " + q);
        return "actors/search";
    }
}
