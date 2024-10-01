package toyProject.demo.team.presentation.dto;

import java.util.Set;

public class TeamRequest {
    private String name;
    private Set<Long> playerIds;

    public TeamRequest() {
    }

    public TeamRequest(String name, Set<Long> playerIds) {
        this.name = name;
        this.playerIds = playerIds;
    }

    public String getName() {
        return name;
    }

    public Set<Long> getPlayerIds() {
        return playerIds;
    }
}
