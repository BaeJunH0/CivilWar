package toyProject.demo.team.application.dto;

import toyProject.demo.team.domain.Team;

public record TeamCommand(String name) {
    public static TeamCommand from(Team team){
        return new TeamCommand(team.getName());
    }
}
