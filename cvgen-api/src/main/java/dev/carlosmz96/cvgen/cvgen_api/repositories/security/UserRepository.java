package dev.carlosmz96.cvgen.cvgen_api.repositories.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.carlosmz96.cvgen.cvgen_api.models.entities.security.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    
}
