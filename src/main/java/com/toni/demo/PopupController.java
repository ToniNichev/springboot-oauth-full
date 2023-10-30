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
    public String popup(@CookieValue(name = "site", defaultValue = "site") String cookieSite,
                        HttpServletResponse response,
                        @RequestParam(name="site", required=false, defaultValue="") String qsSite,
                        @RequestParam(name="page", required=false, defaultValue="") String qsPage,
                        @RequestParam(name="state", required=false, defaultValue="") String qsState,
                        Model model) {

        // Sending state param to the front end to include it in Google request.
        model.addAttribute("state", qsState);

        ResponseCookie resCookieSite = ResponseCookie.from("site", qsSite)
                .httpOnly(false)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(60 * 60 * 10)
                .build();

                response.addHeader("Set-Cookie", resCookieSite.toString());


        ResponseCookie resCookiePage = ResponseCookie.from("page", qsPage)
                .httpOnly(false)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(60 * 60 * 10)
                .build();

        response.addHeader("Set-Cookie", resCookiePage.toString());

        return "popup";
    }
}
