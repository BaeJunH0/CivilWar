package toyProject.demo.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyProject.demo.auth.AuthenticateMember;
import toyProject.demo.auth.JwtTokenProvider;
import toyProject.demo.auth.Token;
import toyProject.demo.user.application.dto.UserCommand;
import toyProject.demo.user.application.dto.UserInfo;
import toyProject.demo.user.presentation.dto.UserRequest;
import toyProject.demo.user.presentation.dto.UserResponse;
import toyProject.demo.user.application.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<Token> register(@RequestBody UserRequest userRequest) {
        userService.makeNewUser(UserCommand.from(userRequest));

        Token token = Token.from(jwtTokenProvider.makeToken(userRequest));
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Token> login(
            @AuthenticateMember UserInfo userInfo,
            @RequestBody UserRequest userRequest
    ) {
        if(!userService.isLogin(UserCommand.from(userRequest))){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Token token = Token.from(jwtTokenProvider.makeToken(userRequest));
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    // 관리자 페이지용
    @GetMapping
    public ResponseEntity<List<UserResponse>> readUsers(@AuthenticateMember UserInfo userInfo) {
        if(!userInfo.admin()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<UserResponse> users = userService.findAll().stream().map(UserResponse::from).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> readUser(
            @AuthenticateMember UserInfo userInfo,
            @PathVariable Long id
    ) {
        if(!userInfo.admin()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserResponse user = UserResponse.from(userService.findOneUser(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(
            @AuthenticateMember UserInfo userInfo,
            @PathVariable Long id,
            @RequestBody UserRequest userRequest
    ) {
        if(!userInfo.admin()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userService.update(id, userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @AuthenticateMember UserInfo userInfo,
            @PathVariable Long id
    ) {
        if(!userInfo.admin()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
