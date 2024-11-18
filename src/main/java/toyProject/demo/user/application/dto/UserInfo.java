package toyProject.demo.user.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import toyProject.demo.user.domain.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfo {
    private String email;
    private String password;
    private String nickname;
    private boolean admin;

    public static UserInfo from(User user){
        return new UserInfo(
                user.getEmail(), user.getPassword(), user.getNickname(), user.isAdmin()
        );
    }
}
