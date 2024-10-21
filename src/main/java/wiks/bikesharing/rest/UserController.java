package wiks.bikesharing.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiks.bikesharing.entity.CreateUserRequest;
import wiks.bikesharing.entity.Token;
import wiks.bikesharing.entity.User;
import wiks.bikesharing.entity.UserAuth;
import wiks.bikesharing.services.AuthService;
import wiks.bikesharing.services.UserService;

@RestController
@RequestMapping("sharing/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public User signUp(@RequestBody CreateUserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PostMapping("/signIn")
    public Token signIn(@RequestBody UserAuth userAuth) {
        return authService.signIn(userAuth);
    }
}
