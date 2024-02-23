package net.leskowsky.tinyap;

import java.util.List;
import java.util.Map;

public record WebfingerAccount(String subject, List<String> aliases, List<Map<String, String>> links) {

    public String actorUrl() {
        var actorLink = links.stream()
                .filter(link -> link.get("type").equals("application/activity+json"))
                .findFirst();

        if (actorLink.isPresent()) {
            return actorLink.get().get("href");
        } else {
            throw new TinyapException("Actor url missing from webfinger account resource");
        }
    }
}
