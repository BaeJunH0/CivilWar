package toyProject.demo.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import toyProject.demo.exception.CustomException;
import toyProject.demo.exception.errorCode.AuthErrorCode;
import toyProject.demo.member.application.dto.MemberInfo;
import toyProject.demo.member.domain.Member;
import toyProject.demo.member.persistence.MemberRepository;

@Component
@RequiredArgsConstructor
public class MemberArgumentResolver implements HandlerMethodArgumentResolver {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticateMember.class);
    }

    @Override
    public MemberInfo resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        String token = webRequest.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException("인증 방식이 잘못되었거나, 토큰이 없습니다!");
        }

        token = token.substring(7);
        // 유효시간 검증
        if(jwtTokenProvider.isTokenExpired(token)) {
            throw CustomException.of(AuthErrorCode.TOKEN_EXPIRED);
        }
        // 클레임 검증 ( Email )
        String email = jwtTokenProvider.getEmailsFromToken(token);
        Member member = memberRepository.findUserByEmail(email).orElseThrow(
                () -> CustomException.of(AuthErrorCode.LOGIN_FAIL)
        );

        return MemberInfo.from(member);
    }
}
