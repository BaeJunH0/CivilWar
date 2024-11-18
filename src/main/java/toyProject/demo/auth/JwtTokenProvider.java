package toyProject.demo.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import toyProject.demo.user.presentation.dto.UserRequest;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtTokenProvider {
    @Value("${key}")
    private static String secretKey;

    @Value("${token-expire-length}")
    private static long validTime;

    public static String makeToken(UserRequest user) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(System.currentTimeMillis());

        return Jwts.builder()
                .claim("email", user.getEmail())
                .issuedAt(now)
                .expiration(new Date(nowMillis + validTime))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    /*
     * 토큰에서 클레임 ( email ) 추출
     */
    public static String getClaimsFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.get("email", String.class);
        } catch(Exception e){
            return "null";
        }
    }
}
