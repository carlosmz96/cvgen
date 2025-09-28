package dev.carlosmz.cvgen.api.cvgenapi.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.carlosmz.cvgen.api.cvgenapi.auth.models.entities.User;



public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
