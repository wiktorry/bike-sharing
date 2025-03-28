package wiks.bikesharing.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.CreateUserRequest;
import wiks.bikesharing.entity.Rental;
import wiks.bikesharing.entity.User;
import wiks.bikesharing.exceptions.BadRequestException;
import wiks.bikesharing.repositories.UserRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(CreateUserRequest userRequest) {
        throwIfUserExists(userRequest);
        User user = new User(
                0,
                userRequest.username(),
                userRequest.email(),
                passwordEncoder.encode(userRequest.password()),
                userRequest.role(),
                new ArrayList<Rental>()
        );
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    private void throwIfUserExists(CreateUserRequest user) {
        if (userRepository.existsByUsername(user.username())) {
            throw new BadRequestException("Username already exists in database");
        } else if (userRepository.existsByEmail(user.email())) {
            throw new BadRequestException("Email already exists in database");
        }
    }
}
