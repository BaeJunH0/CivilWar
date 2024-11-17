package toyProject.demo.user.presentation.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyProject.demo.user.domain.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserResponse {
    private String email;
    private String nickname;

    public static UserResponse from(User user){
        return new UserResponse(user.getEmail(), user.getNickname());
    }
}
