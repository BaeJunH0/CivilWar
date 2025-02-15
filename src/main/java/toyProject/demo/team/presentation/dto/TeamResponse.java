package toyProject.demo.team.presentation.dto;

import toyProject.demo.team.application.dto.TeamCommand;

public record TeamResponse(String name) {
    public static TeamResponse from(TeamCommand teamCommand){
        return new TeamResponse(teamCommand.name());
    }
}
