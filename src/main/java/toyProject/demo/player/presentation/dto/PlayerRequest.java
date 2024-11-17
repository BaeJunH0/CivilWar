package toyProject.demo.player.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import toyProject.demo.player.domain.Player;

@Getter
@NoArgsConstructor
public class PlayerRequest {
    private int level;
    private String nickname;
    private String freeTier;
    private String soloTier;

    public static Player toEntityFrom(PlayerRequest playerRequest){
        return Player.of(
                playerRequest.getLevel(),
                playerRequest.getNickname(),
                playerRequest.getFreeTier(),
                playerRequest.getSoloTier()
        );
    }
}
