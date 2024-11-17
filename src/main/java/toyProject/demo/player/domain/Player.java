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
    @Column(nullable = false)
    private String riotId;
    @Column(nullable = false)
    private String riotTag;

    private Player(String riotId, String riotTag){
        this.riotId = riotId;
        this.riotTag = riotTag;
    }

    public static Player of(String riotId, String riotTag){
        return new Player(riotId, riotTag);
    }
}
