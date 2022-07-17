package com.accounting.backend.auth;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, String> {
    Optional<User> findByUsername(String username);
    void save(User user);
}
