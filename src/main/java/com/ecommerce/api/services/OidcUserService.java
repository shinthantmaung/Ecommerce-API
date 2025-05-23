package com.ecommerce.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OidcUserService extends org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService {
    private final UserService userService;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("here it reached");
        OidcUser oAuth2User = super.loadUser(userRequest);
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        userService.createUser(name, email);
        return oAuth2User;
    }
}
