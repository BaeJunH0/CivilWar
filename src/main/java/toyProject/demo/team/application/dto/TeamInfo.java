package toyProject.demo.team.application.dto;

import toyProject.demo.team.domain.Team;

public record TeamInfo(String name) {
    public static TeamInfo from(Team){

    }
}
