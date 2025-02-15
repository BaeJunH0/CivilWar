package toyProject.demo.user.application.dto;

import toyProject.demo.user.domain.User;

public record UserInfo(String email, String nickname, boolean admin) {
    public static UserInfo from(User user){
        return new UserInfo(
                user.getEmail(), user.getNickname(), user.isAdmin()
        );
    }
}
