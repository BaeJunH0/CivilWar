package toyProject.demo.player.presentation.dto;

import toyProject.demo.player.application.dto.PlayerInfo;

public record PlayerResponse(String nickname, String tag, String soloRank, String freeRank) {
    public static PlayerResponse of(PlayerInfo playerInfo) {
        return new PlayerResponse(
                playerInfo.nickname(),
                playerInfo.tag(),
                playerInfo.soloTier(),
                playerInfo.freeTier()
        );
    }
}
