package toyProject.demo.player.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String puuid;

    @Column
    private String nickname;

    @Column
    private String tag;

    @Column
    private String soloTier;

    @Column
    private String freeTier;

    private Player(String puuid, String nickname, String tag){
        this.puuid = puuid;
        this.nickname = nickname;
        this.tag = tag;
    }

    public static Player of(String puuid, String nickname, String tag) {
        return new Player(puuid, nickname, tag);
    }

    public void addTierInfo(String soloTier, String freeTier) {
        this.soloTier = soloTier;
        this.freeTier = freeTier;
    }
}
