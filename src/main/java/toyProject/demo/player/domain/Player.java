package toyProject.demo.player.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyProject.demo.player.presentation.dto.PlayerRequest;

@Table(name = "players")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "level", nullable = false)
    private int level;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "freeTier")
    private String freeTier;
    @Column(name = "soloTier")
    private String soloTier;

    private Player(int level, String nickname, String freeTier, String soloTier){
        this.level = level;
        this.nickname = nickname;
        this.freeTier = freeTier;
        this.soloTier = soloTier;
    }

    public static Player of(int level, String nickname, String freeTier, String soloTier){
        return new Player(level, nickname, freeTier, soloTier);
    }

    public void update(PlayerRequest playerRequest) {
        this.level = playerRequest.getLevel();
        this.freeTier = playerRequest.getFreeTier();
        this.soloTier = playerRequest.getSoloTier();
        this.nickname = playerRequest.getNickname();
    }
}
