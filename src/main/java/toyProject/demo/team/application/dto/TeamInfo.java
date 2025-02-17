package toyProject.demo.team.application.dto;

import toyProject.demo.player.application.dto.PlayerInfo;
import toyProject.demo.team.domain.Team;

import java.util.List;

public class TeamInfo {
    public record Basic(Long id, String name, String owner) {
        public static Basic of(Team team) {
            return new Basic(
                    team.getId(),
                    team.getName(),
                    team.getOwner()
            );
        }
    }

    public record Detail(List<PlayerInfo> playerInfos) {
        public static Detail of(List<PlayerInfo> playerInfos) {
            return new Detail(playerInfos);
        }
    }
}
