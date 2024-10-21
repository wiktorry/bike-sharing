package wiks.bikesharing.services;

import wiks.bikesharing.entity.Token;
import wiks.bikesharing.entity.User;
import wiks.bikesharing.entity.UserAuth;

public interface UserService {
    Token signUp(User user);

    Token signIn(UserAuth user);

    User getCurrentUser();

    void userExists(User user);
}
