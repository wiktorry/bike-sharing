package wiks.bikesharing.services;

import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.Token;
import wiks.bikesharing.entity.User;
import wiks.bikesharing.entity.UserAuth;

@Service
public interface AuthService {
    Token signIn(UserAuth userAuth);

    User getCurrentUser();
}
