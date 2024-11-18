package toyProject.demo.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Token {
    private String accessToken;

    public static Token from(String accessToken){
        return new Token(accessToken);
    }
}
