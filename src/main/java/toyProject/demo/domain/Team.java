package toyProject.demo.domain;

import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
