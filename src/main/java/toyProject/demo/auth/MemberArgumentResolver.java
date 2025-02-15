package toyProject.demo.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import toyProject.demo.user.application.UserService;
import toyProject.demo.user.application.dto.UserInfo;

@Component
@RequiredArgsConstructor
public class MemberArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticateMember.class);
    }

    @Override
    public UserInfo resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        String token = webRequest.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException("인증 방식이 잘못되었거나, 토큰이 없습니다!");
        }

        token = token.substring(7); // "Bearer " 부분을 제거

        String email = jwtTokenProvider.getEmailsFromToken(token);
        if(!userService.isExistedUser(email)) {
            throw new IllegalArgumentException("유효하지 않은 로그인 정보입니다!");
        }

        return userService.loginUser(email);
    }
}
