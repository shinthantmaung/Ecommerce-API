package com.ecommerce.api.services.Impls;

import com.ecommerce.api.domain.Role;
import com.ecommerce.api.domain.entities.User;
import com.ecommerce.api.repositories.UserRepository;
import com.ecommerce.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public void createUser(String name, String email) {
        if(userRepository.existsByEmail(email.toLowerCase()))
        {
            throw new IllegalArgumentException("user with email already exists");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);

        User user = User.builder()
                .name(name)
                .email(email.toLowerCase())
                .roles(roles)
                .build();
        userRepository.save(user);
    }
}
