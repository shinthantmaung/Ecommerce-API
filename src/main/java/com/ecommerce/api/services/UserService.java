package com.ecommerce.api.services;

import org.springframework.stereotype.Service;

public interface UserService {
    void createUser(String name, String email);
}
