package wiks.bikesharing.entity;

public record CreateUserRequest(String username, String email, String password, Role role) {
}
