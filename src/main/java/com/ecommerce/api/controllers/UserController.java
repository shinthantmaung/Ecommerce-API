package com.ecommerce.api.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/hello")
    public String oauth2test(@AuthenticationPrincipal OAuth2User user)
    {
        return "welcome, " + user.getAttribute("name");
    }

    @GetMapping("/")
    public String home()
    {
        return "hello from ecommerce api server, you can access /hello";
    }
}
