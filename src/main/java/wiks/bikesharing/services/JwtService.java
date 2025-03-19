package wiks.bikesharing.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String jwt);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String jwt, UserDetails userDetails);

    boolean isTokenExpired(String jwt);
}
