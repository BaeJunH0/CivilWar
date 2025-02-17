package toyProject.demo.member.application.dto;

import toyProject.demo.member.domain.Member;
import toyProject.demo.member.presentation.dto.MemberRequest;

public record MemberCommand(String email, String nickname, String password) {

    public static MemberCommand from(MemberRequest.Register memberRequest){
        return new MemberCommand(
                memberRequest.email(), memberRequest.nickname(), memberRequest.password()
        );
    }

    public static MemberCommand from(MemberRequest.Login memberRequest){
        return new MemberCommand(
                memberRequest.email(), null, memberRequest.password()
        );
    }

    public static Member toEntity(MemberCommand memberCommand){
        return Member.of(memberCommand.email(), memberCommand.password(), memberCommand.nickname());
    }
}
