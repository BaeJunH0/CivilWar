package toyProject.demo.player.application.dto;

import toyProject.demo.player.domain.Player;

public record PlayerInfo(
        Long id,
        String nickname,
        String tag,
        String soloTier,
        String freeTier
) {
    public static PlayerInfo of(Player player) {
        return new PlayerInfo(
                player.getId(),
                player.getNickname(),
                player.getTag(),
                player.getSoloTier(),
                player.getFreeTier()
        );
    }
}
