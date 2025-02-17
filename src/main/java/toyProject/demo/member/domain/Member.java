package toyProject.demo.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "password")
    private UUID password;
    
    @Column(name = "admin")
    private boolean admin;

    private Member(String email, String password, String nickname){
        this.email = email;
        this.password = makePassword(password);
        this.nickname = nickname;
        this.admin = false;
    }

    public static Member of(String email, String password, String nickname){
        return new Member(email, password, nickname);
    }

    public static UUID makePassword(String password) {
        long seed = password.hashCode();
        return new UUID(seed, ~seed);
    }

    public void changeName(String nickname) {
        this.nickname = nickname;
    }
}
