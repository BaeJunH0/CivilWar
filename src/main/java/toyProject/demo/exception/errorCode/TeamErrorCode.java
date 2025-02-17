package toyProject.demo.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum TeamErrorCode implements ErrorCode{
    NOT_FOUND(HttpStatus.NOT_FOUND, "T001", "No such team info"),
    NOT_MY_TEAM(HttpStatus.CONFLICT, "T002", "You are not owner this team");

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
