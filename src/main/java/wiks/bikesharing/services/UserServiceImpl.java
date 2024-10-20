package wiks.bikesharing.services;

import org.springframework.stereotype.Service;
import wiks.bikesharing.entity.Token;
import wiks.bikesharing.entity.User;
import wiks.bikesharing.entity.UserAuth;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Token signUp(User user) {
        return null;
    }

    @Override
    public Token signIn(UserAuth user) {
        return null;
    }
}
