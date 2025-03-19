package wiks.bikesharing.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.Token;
import wiks.bikesharing.entity.User;
import wiks.bikesharing.entity.UserAuth;
import wiks.bikesharing.exceptions.BadRequestException;
import wiks.bikesharing.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public Token signIn(UserAuth userAuth) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuth.getUsername(),
                        userAuth.getPassword()
                )
        );
        User user = userRepository.findByUsername(userAuth.getUsername())
                .orElseThrow(() -> new BadRequestException("User doesn't exist in database"));
        return new Token(jwtService.generateToken(user));
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
}
