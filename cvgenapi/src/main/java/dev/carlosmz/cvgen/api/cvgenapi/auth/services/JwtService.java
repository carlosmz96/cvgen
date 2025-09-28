package dev.carlosmz.cvgen.api.cvgenapi.auth.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    public String generateToken(Authentication authentication);

    public String getUsernameFromToken(String token);

    public boolean validateToken(String token, UserDetails userDetails);

}
