package toyProject.demo.player.presentation.dto;

import toyProject.demo.player.application.dto.PlayerInfo;

public record PlayerResponse(String riotId, String riotTag) {
    public static PlayerResponse from(PlayerInfo playerInfo){
        return new PlayerResponse(playerInfo.riotId(), playerInfo.riotTag());
    }
}
