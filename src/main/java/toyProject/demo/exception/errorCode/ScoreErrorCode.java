package toyProject.demo.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ScoreErrorCode implements ErrorCode{
    CALCULATING_ERROR(HttpStatus.BAD_REQUEST, "S001", "Score calculating process fail");

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
