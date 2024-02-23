package net.leskowsky.tinyap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WebfingerClient {

    public static final Logger logger = LoggerFactory.getLogger(WebfingerClient.class);

    public WebfingerAccount getAccount(String user, String domain) {

        logger.info("username=" + user);
        logger.info("domain=" + domain);

        var httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        var url = String.format("https://%s/.well-known/webfinger?resource=acct:%s@%s", domain, user, domain);
        logger.info("webfinger_url=" + url);

        var req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            long start = System.currentTimeMillis();
            var resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
            logger.info("response_time_ms=" + (System.currentTimeMillis() - start));
            logger.info("status_code=" + resp.statusCode());

            ObjectMapper objectMapper = new ObjectMapper();
            var webfingerAccount = objectMapper.readValue(resp.body(), WebfingerAccount.class);
            logger.info("account_subject=" + webfingerAccount.subject());
            logger.info("actor_url=" + webfingerAccount.actorUrl());

            return webfingerAccount;
        } catch (IOException | InterruptedException e) {
            throw new TinyapException(e);
        }
    }
}