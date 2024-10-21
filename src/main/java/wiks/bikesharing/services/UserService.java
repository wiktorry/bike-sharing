package wiks.bikesharing.services;

import wiks.bikesharing.entity.CreateUserRequest;
import wiks.bikesharing.entity.User;

public interface UserService {
    User createUser(CreateUserRequest user);
}
