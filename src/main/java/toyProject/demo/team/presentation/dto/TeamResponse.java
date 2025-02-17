package toyProject.demo.team.presentation.dto;

import toyProject.demo.player.presentation.dto.PlayerResponse;
import toyProject.demo.team.application.dto.TeamInfo;

import java.util.List;

public class TeamResponse {
    public record Basic(Long id, String name, String owner) {
        public static TeamResponse.Basic of(TeamInfo.Basic teamInfo) {
            return new TeamResponse.Basic(
                    teamInfo.id(),
                    teamInfo.name(),
                    teamInfo.owner()
            );
        }
    }

    public record Detail(List<PlayerResponse> playerResponses) {
        public static TeamResponse.Detail of(TeamInfo.Detail teamInfo) {
            return new TeamResponse.Detail(
                    teamInfo.playerInfos().stream().map(PlayerResponse::of).toList()
            );
        }
    }
}
