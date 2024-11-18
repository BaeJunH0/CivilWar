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
import toyProject.demo.user.domain.User;
import toyProject.demo.user.presentation.dto.UserRequest;
import toyProject.demo.user.presentation.dto.UserResponse;
import toyProject.demo.user.application.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    // 토큰 x
    @PostMapping("/register")
    public ResponseEntity<Token> register(@RequestBody UserRequest userRequest){
        userService.save(UserCommand.from(userRequest));
        Token token = Token.from(JwtTokenProvider.makeToken(userRequest));
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    // 토큰 o
    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody UserRequest userRequest){
        if(!userService.isLogin(UserCommand.from(userRequest))){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Token token = Token.from(JwtTokenProvider.makeToken(userRequest));
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> readUsers(){
        List<UserResponse> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> readUser(@PathVariable Long id){
        UserResponse user = userService.findOneUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        userService.update(id, userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
