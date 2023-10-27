package com.toni.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import static java.net.http.HttpRequest.BodyPublishers.*;


@Controller

public class CallbackController {
    @GetMapping("/callback")
    public String login(@RequestParam(name="code", required=false, defaultValue="xxx") String qsCode,
                        Model model) throws InterruptedException {

        // String uri = "https://public.toni-develops.com/";
        String uri = "https://www.googleapis.com/oauth2/v4/token";

        var values = new HashMap<String, String>() {{
            put("grant_type", "authorization_code");
            put ("client_id", "989056576533-mtef8cl5is5ogjh3np580ireurns7l5k.apps.googleusercontent.com");
            put ("client_secret", "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");
            put ("redirect_uri", "https://toninichev.com:8080/callback?provider=google");
            put ("code", qsCode);
        }};

        var objectMapper = new ObjectMapper();
        String requestBody;

        try {
            requestBody = objectMapper.writeValueAsString(values);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response
            System.out.println(response.body());

        } catch (IOException e) {
            System.out.println(e);
        }





        return "callback";
    }
}
