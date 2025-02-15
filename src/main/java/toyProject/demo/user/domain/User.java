package toyProject.demo.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "user")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
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

    private User(String email, String password, String nickname){
        this.email = email;
        this.password = UUID.fromString(password);
        this.nickname = nickname;
        this.admin = false;
    }

    public static User of(String email, String password, String nickname){
        return new User(email, password, nickname);
    }

    public boolean passCheck(User user){
        if(user.getPassword().equals(this.password)){
            return true;
        }
        return false;
    }
    public void changeName(String nickname) {
        this.nickname = nickname;
    }
}
