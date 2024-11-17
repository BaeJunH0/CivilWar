package toyProject.demo.player.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import toyProject.demo.player.domain.Player;

@Getter
@NoArgsConstructor
public class PlayerResponse {
    private int level;
    private String nickname;
    private String freeTier;
    private String soloTier;

    private PlayerResponse(int level, String nickname, String freeTier, String soloTier) {
        this.level = level;
        this.nickname = nickname;
        this.freeTier = freeTier;
        this.soloTier = soloTier;
    }

    public static PlayerResponse from(Player player){
        return new PlayerResponse(

        );
    }
}
