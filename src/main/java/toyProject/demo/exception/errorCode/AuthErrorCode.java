package toyProject.demo.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum AuthErrorCode implements ErrorCode {
    ALREADY_EXIST_EMAIL(HttpStatus.CONFLICT, "A001", "using already exist email is now allowed"),
    LOGIN_FAIL(HttpStatus.CONFLICT, "A002", "email or password is wrong!"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "A003", "No such member info"),
    TOKEN_EXPIRED(HttpStatus.CONFLICT, "A004", "Token is expired!");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public String code() {
        return errorCode;
    }

    @Override
    public String message() {
        return message;
    }
}
