package com.toni.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class LoginController {
    @GetMapping("/login")
    public String login(@CookieValue(name = "my-cookie", defaultValue = "default-user-id") String userId,
                        @RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        long unixTimestamp = System.currentTimeMillis();

        model.addAttribute("name", name);
        model.addAttribute("time", userId);
        return "login";
    }
}
