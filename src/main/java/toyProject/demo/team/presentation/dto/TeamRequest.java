package toyProject.demo.team.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import toyProject.demo.team.domain.Team;

import java.util.Set;

@Getter
@NoArgsConstructor
public class TeamRequest {
    private String name;
    private Set<Long> playerIds;

    public static Team toEntityFrom(TeamRequest teamRequest){
        return Team.of(teamRequest.getName());
    }
}
