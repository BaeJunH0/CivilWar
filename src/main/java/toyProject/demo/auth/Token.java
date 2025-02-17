package toyProject.demo.auth;

public record Token(String accessToken) {
    public static Token from(String accessToken){
        return new Token(accessToken);
    }
}
