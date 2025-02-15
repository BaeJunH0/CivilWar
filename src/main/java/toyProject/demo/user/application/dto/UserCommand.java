package toyProject.demo.user.application.dto;

import toyProject.demo.user.domain.User;
import toyProject.demo.user.presentation.dto.UserRequest;

public record UserCommand(String email, String nickname, String password) {

    public static UserCommand from(UserRequest userRequest){
        return new UserCommand(
                userRequest.email(), userRequest.nickname(), userRequest.password()
        );
    }

    public static User toEntityFrom(UserCommand userCommand){
        return User.of(userCommand.email(), userCommand.password(), userCommand.nickname());
    }
}
