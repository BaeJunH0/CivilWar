package toyProject.demo.DTO.team;

import toyProject.demo.domain.Player;
import toyProject.demo.domain.Team;

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
