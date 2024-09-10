package toyProject.demo.DTO.user;

import toyProject.demo.domain.User;

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
