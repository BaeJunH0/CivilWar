package toyProject.demo.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyProject.demo.auth.AuthenticateMember;
import toyProject.demo.auth.JwtTokenProvider;
import toyProject.demo.auth.Token;
import toyProject.demo.member.application.dto.MemberCommand;
import toyProject.demo.member.application.dto.MemberInfo;
import toyProject.demo.member.presentation.dto.MemberRequest;
import toyProject.demo.member.application.MemberService;
import toyProject.demo.member.presentation.dto.MemberResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MemberController {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<Token> register(@RequestBody MemberRequest.Register memberRequest) {
        memberService.register(MemberCommand.from(memberRequest));

        Token token = Token.from(jwtTokenProvider.makeToken(memberRequest.email()));
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody MemberRequest.Login memberRequest) {
        memberService.login(MemberCommand.from(memberRequest));

        Token token = Token.from(jwtTokenProvider.makeToken(memberRequest.email()));
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    // 내 정보 읽기
    @GetMapping("/my")
    public ResponseEntity<MemberResponse> readUser(
            @AuthenticateMember MemberInfo memberInfo
    ) {
        MemberResponse user = MemberResponse.from(memberService.getMyInfo(memberInfo.email()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // 내 닉네임 변경
    @PatchMapping("my")
    public ResponseEntity<Void> updateUser(
            @AuthenticateMember MemberInfo memberInfo,
            @RequestBody String nickname
    ) {
        memberService.changeNickname(memberInfo.email(), nickname);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
