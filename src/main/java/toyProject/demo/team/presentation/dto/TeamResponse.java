package toyProject.demo.team.presentation.dto;

import toyProject.demo.player.domain.Player;
import toyProject.demo.team.domain.Team;

import java.util.Set;

public class TeamResponse {
    private String name;
    private Set<Player> players;

    public TeamResponse(){

    }

    public TeamResponse(Team team) {
        this.name = team.getName();
        this.players = team.getPlayers();
    }

    public String getName() {
        return name;
    }

    public Set<Player> getPlayers() {
        return players;
    }
}
