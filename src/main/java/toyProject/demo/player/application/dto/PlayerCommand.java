package toyProject.demo.player.application.dto;

import toyProject.demo.player.presentation.dto.PlayerRequest;

public record PlayerCommand(String riotId, String riotTag) {
    public static PlayerCommand from(PlayerRequest playerRequest){
        return new PlayerCommand(playerRequest.riotId(), playerRequest.riotTag());
    }
}
