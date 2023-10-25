package com.toni.demo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.ResponseCookie;
import org.springframework.http.HttpHeaders;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;


@Controller

public class PopupController {

    @GetMapping("/popup")
    public String popup(@CookieValue(name = "my-cookie", defaultValue = "default-user-id") String userId,
                        HttpServletResponse response,
                        @RequestParam(name="site", required=false, defaultValue="World") String site, Model model) {

        System.out.println("Hello, world!");
        System.out.println(userId);

        ResponseCookie resCookie = ResponseCookie.from("my-cookie", "my-cookie-value")
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(60 * 60 * 10)
                .build();

                response.addHeader("Set-Cookie", resCookie.toString());

        return "popup";
    }
}
