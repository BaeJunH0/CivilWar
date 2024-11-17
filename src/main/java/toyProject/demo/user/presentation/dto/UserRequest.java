package toyProject.demo.user.presentation.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyProject.demo.user.domain.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequest {
    private String email;
    private String nickname;
    private String password;

    public static User toEntityFrom(UserRequest userRequest){
        return User.of(userRequest.getEmail(), userRequest.getPassword(), userRequest.getNickname());
    }
}
