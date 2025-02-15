package toyProject.demo.user.presentation.dto;

import toyProject.demo.user.application.dto.UserInfo;

public record UserResponse(String email, String nickname) {
    public static UserResponse from(UserInfo userInfo){
        return new UserResponse(userInfo.email(), userInfo.nickname());
    }
}
