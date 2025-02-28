package toyProject.demo.team.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toyProject.demo.player.application.PlayerService;
import toyProject.demo.player.application.dto.PlayerInfo;
import toyProject.demo.player.domain.Player;
import toyProject.demo.team.application.dto.TeamInfo;
import toyProject.demo.team.domain.Team;
import toyProject.demo.team.presentation.dto.TeamRequest;

import java.util.*;

@Component
@RequiredArgsConstructor
public class TeamFacade {
    private final PlayerService playerService;
    private final TeamService teamService;

    public Long saveTeamPlayer(TeamRequest teamRequest, String owner) {
        Map<Integer, Player> players = playerService.reqToEnt(teamRequest.playerRequests());

        Team team = teamService.save(teamRequest.name(), owner);

        for (Map.Entry<Integer, Player> playerEntry : players.entrySet()) {
            playerService.playerJoinToTeam(team, playerEntry.getValue(), playerEntry.getKey());
        }

        return team.getId();
    }

    public TeamInfo.Detail shufflePlayer(Long id) {
        TeamInfo.Detail team = teamService.findOneTeam(id);

        List<PlayerInfo> playerInfos = ShuffleUtil.shuffle(team.playerInfos());

        List<PlayerInfo> resultList = new ArrayList<>(10);
        // even
        for(int i = 0; i < 10; i++) {
            if(i % 2 == 0) {
                resultList.add(playerInfos.get(i));
            }
        }
        // odd
        for(int i = 0; i < 10; i++) {
            if(i % 2 == 1) {
                resultList.add(playerInfos.get(i));
            }
        }

        return TeamInfo.Detail.of(resultList, team.name());
    }
}
