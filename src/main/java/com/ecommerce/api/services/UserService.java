package com.ecommerce.api.services;

import com.ecommerce.api.domain.Role;
import com.ecommerce.api.domain.entities.User;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface UserService {
    void createUser(String name, String email, Set<Role> roles);

    boolean existsByEmail(String email);

    User getUserByEmail(String email);
}
