package dev.carlosmz96.cvgen.cvgen_api.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.carlosmz96.cvgen.cvgen_api.security.models.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    
}
