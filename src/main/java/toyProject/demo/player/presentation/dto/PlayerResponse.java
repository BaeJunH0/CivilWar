package toyProject.demo.player.presentation.dto;

import toyProject.demo.player.application.dto.PlayerInfo;

public record PlayerResponse(String nickname, String tag) {
    public static PlayerResponse of(PlayerInfo playerInfo) {
        return new PlayerResponse(
                playerInfo.nickname(),
                playerInfo.tag()
        );
    }
}
