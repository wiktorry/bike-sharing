package wiks.bikesharing.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateUserRequest {
    private String username;
    private String email;
    private String password;
    private Role role;
}
