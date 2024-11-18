package toyProject.demo.team.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import toyProject.demo.player.presentation.dto.PlayerResponse;
import toyProject.demo.team.domain.Team;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class TeamResponse {
    private String name;
    private Set<PlayerResponse> players;

    private TeamResponse(String name, Set<PlayerResponse> players){
        this.name = name;
        this.players = players;
    }
}
