package com.ecommerce.api.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String test;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/hello")
    public String oauth2test(@AuthenticationPrincipal OidcUser user)
    {
        return "welcome, " + user.getAttribute("name");
    }

    @GetMapping("/")
    public String home()
    {
//        return "hello from ecommerce api server, you can access /hello";
        return test;
    }
}
