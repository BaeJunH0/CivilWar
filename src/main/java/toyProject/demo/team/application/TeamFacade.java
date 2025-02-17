package toyProject.demo.team.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toyProject.demo.player.application.PlayerService;
import toyProject.demo.player.domain.Player;
import toyProject.demo.team.domain.Team;
import toyProject.demo.team.presentation.dto.TeamRequest;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamFacade {
    private final PlayerService playerService;
    private final TeamService teamService;

    public void saveTeamPlayer(TeamRequest teamRequest, String owner) {
        List<Player> players = playerService.reqToEnt(teamRequest.playerRequests());

        Team team = teamService.save(teamRequest.name(), owner);

        for (Player player : players) {
            playerService.playerJoinToTeam(team, player);
        }
    }

    public void updateTeamPlayer(TeamRequest teamRequest, String owner, Long id) {
        List<Player> players = playerService.reqToEnt(teamRequest.playerRequests());

        Team team = teamService.findByOwnerAndId(owner, id);

        teamService.cleanTeam(team);

        for (Player player : players) {
            playerService.playerJoinToTeam(team, player);
        }
    }
}
