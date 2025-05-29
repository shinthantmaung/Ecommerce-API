package com.ecommerce.api.services;

import com.ecommerce.api.domain.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOidcUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    private final UserService userService;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        // Delegate to the default service
        System.out.println("here it started");
        log.info("here it started");
        OidcUserService delegate = new OidcUserService();
        OidcUser oidcUser = delegate.loadUser(userRequest);

        Set<GrantedAuthority> authorities = new HashSet<>();
        Set<Role> roles = new HashSet<>();

        String name = oidcUser.getAttribute("name");
        String email = oidcUser.getAttribute("email");
        authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.toString()));
        roles.add(Role.ROLE_USER);
        if (email.equals("shinthantmg224@gmail.com"))
        {
            authorities.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString()));
            authorities.add(new SimpleGrantedAuthority(Role.ROLE_VENDOR.toString()));
            roles.add(Role.ROLE_ADMIN);
            roles.add(Role.ROLE_VENDOR);
        }
        if(!userService.existsByEmail(email))
        {
            log.info("here it created");
            userService.createUser(name, email,roles);
        }

        return new DefaultOidcUser(authorities,oidcUser.getIdToken(),oidcUser.getUserInfo());
    }
}