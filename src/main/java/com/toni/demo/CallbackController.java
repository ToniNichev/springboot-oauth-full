package com.toni.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.http.HttpResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.HashMap;
import static com.toni.demo.MD5.getMD5;

@Controller

public class CallbackController {
    @GetMapping("/callback")
    public String login(
            @RequestParam(name="code", required=false, defaultValue="xxx") String qsCode,
            @RequestParam(name="state", required=false, defaultValue="xxx") String qsState,
                        Model model) throws InterruptedException {

        String stateHash = getMD5("passphrase-123");

        System.out.println("STATE:");
        System.out.println(qsState);

        System.out.println("stateHash:");
        System.out.println(stateHash);

        if(!qsState.equals(stateHash)) {
            System.out.println("STATE DON'T MATCH !!!!");
            return "forbidden";
        }

        // @to-do: add this to external config file
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

        // @to-do: use java library to exchange code for JWT
        try {
            requestBody = objectMapper.writeValueAsString(values);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Response with JWT
            model.addAttribute("response", response.body());

        } catch (IOException e) {
            System.out.println(e);
        }





        return "callback";
    }
}
