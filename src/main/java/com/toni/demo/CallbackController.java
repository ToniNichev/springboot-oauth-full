package com.toni.demo;


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

        String uri = "https://public.toni-develops.com/";


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();




        try {
            // Execute the request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response
            System.out.println(response.body());
        } catch (IOException e) {
            System.out.println(e);
        }

        return "callback";
    }
}
