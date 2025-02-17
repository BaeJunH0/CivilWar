package toyProject.demo.member.presentation.dto;

import toyProject.demo.member.application.dto.MemberInfo;

public record MemberResponse(String email, String nickname) {
    public static MemberResponse from(MemberInfo memberInfo){
        return new MemberResponse(memberInfo.email(), memberInfo.nickname());
    }
}
