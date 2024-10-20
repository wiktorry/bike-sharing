package wiks.bikesharing.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiks.bikesharing.entity.Token;
import wiks.bikesharing.entity.User;
import wiks.bikesharing.entity.UserAuth;
import wiks.bikesharing.services.UserService;

@RestController
@RequestMapping("sharing/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public Token signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @PostMapping("/signIn")
    public Token signIn(@RequestBody UserAuth userAuth) {
        return userService.signIn(userAuth);
    }
}
