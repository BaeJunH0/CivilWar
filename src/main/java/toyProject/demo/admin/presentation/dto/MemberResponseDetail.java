package toyProject.demo.admin.presentation.dto;

import toyProject.demo.member.application.dto.MemberInfo;

public record MemberResponseDetail(Long id, String email, String nickname, boolean isAdmin) {
    public static MemberResponseDetail from(MemberInfo memberInfo) {
        return new MemberResponseDetail(
                memberInfo.id(),
                memberInfo.email(),
                memberInfo.nickname(),
                memberInfo.admin()
        );
    }
}
