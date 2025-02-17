package toyProject.demo.member.presentation.dto;


public class MemberRequest {
    public record Register(String email, String nickname, String password) {

    }

    public record Login(String email, String password) {

    }
}
