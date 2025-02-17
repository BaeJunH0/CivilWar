package toyProject.demo.member.application.dto;

import toyProject.demo.member.domain.Member;

public record MemberInfo(String email, String nickname, boolean admin) {
    public static MemberInfo from(Member member){
        return new MemberInfo(
                member.getEmail(), member.getNickname(), member.isAdmin()
        );
    }
}
