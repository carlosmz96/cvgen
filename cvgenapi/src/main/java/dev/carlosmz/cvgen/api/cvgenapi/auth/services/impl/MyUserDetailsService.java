package dev.carlosmz.cvgen.api.cvgenapi.auth.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.carlosmz.cvgen.api.cvgenapi.auth.models.entities.MyUserDetails;
import dev.carlosmz.cvgen.api.cvgenapi.auth.models.entities.User;
import dev.carlosmz.cvgen.api.cvgenapi.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new MyUserDetails(user);
    }
    
}