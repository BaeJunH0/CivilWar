package toyProject.demo.user.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import toyProject.demo.user.domain.User;
import toyProject.demo.user.presentation.dto.UserRequest;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserCommand {
    private String email;
    private String nickname;
    private String password;

    public static UserCommand from(UserRequest userRequest){
        return new UserCommand(
                userRequest.getEmail(), userRequest.getNickname(), userRequest.getPassword()
        );
    }

    public static User toEntityFrom(UserCommand userCommand){
        return User.of(userCommand.getEmail(), userCommand.getPassword(), userCommand.getNickname());
    }
}
