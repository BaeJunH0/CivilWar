package toyProject.demo.player.application.dto;

import toyProject.demo.player.presentation.dto.PlayerRequest;

public record PlayerCommand(String nickname, String tag) {
    public static PlayerCommand to(PlayerRequest playerRequest) {
        return new PlayerCommand(
                playerRequest.nickname(),
                playerRequest.tag()
        );
    }
}
