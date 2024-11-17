package toyProject.demo.player.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import toyProject.demo.player.domain.Player;

@Getter
@NoArgsConstructor
public class PlayerRequest {
    private String riotId;
    private String riotTag;

    public static Player toEntityFrom(PlayerRequest playerRequest){
        return Player.of(
                playerRequest.getRiotId(),
                playerRequest.getRiotTag()
        );
    }
}
