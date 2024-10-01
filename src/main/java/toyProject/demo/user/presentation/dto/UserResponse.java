package toyProject.demo.user.presentation.dto;

import toyProject.demo.user.domain.User;

public class UserResponse {
    private String email;
    private String nickname;

    public UserResponse() {
    }

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
