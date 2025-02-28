package toyProject.demo.player.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyProject.demo.team.domain.Team;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "index")
    private int index;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private TeamPlayer(Team team, Player player, int index) {
        this.team = team;
        this.player = player;
        this.index = index;
    }

    public static TeamPlayer of(Team team, Player player, int index) {
        return new TeamPlayer(team, player, index);
    }
}
