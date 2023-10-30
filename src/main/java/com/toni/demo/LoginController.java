package com.toni.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static com.toni.demo.MD5.getMD5;

@Controller

public class LoginController {
    @GetMapping("/login")
    public String login(@CookieValue(name = "my-cookie", defaultValue = "default-user-id") String userId,
                        @RequestParam(name="name", required=false, defaultValue="World") String name,
                        Model model) {

        long unixTimestamp = System.currentTimeMillis();

        /*
        Getting state parameter as a hash
        The state parameter in OAuth 2.0 is used to prevent CSRF attacks.
        The state parameter is a unique and non-guessable value associated with each authentication request.
        The value is used to confirm that the value coming from the response matches the one sent in the request.
         */
        String hash = getMD5("passphrase-123");

        model.addAttribute("state", hash);
        model.addAttribute("time", unixTimestamp);
        return "login";
    }
}
