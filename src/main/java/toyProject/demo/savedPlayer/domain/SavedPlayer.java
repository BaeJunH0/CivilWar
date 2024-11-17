package toyProject.demo.savedPlayer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import toyProject.demo.player.domain.Player;
import toyProject.demo.team.domain.Team;

@Table(name = "savedPlayer")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SavedPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Player player;
    @ManyToOne
    private Team team;
}
