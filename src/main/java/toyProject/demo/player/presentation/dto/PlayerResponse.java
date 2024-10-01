package toyProject.demo.player.presentation.dto;

import toyProject.demo.player.domain.Player;

public class PlayerResponse {
    private int level;
    private String nickname;
    private String freeTier;
    private String soloTier;

    public PlayerResponse(Player player) {
        this.level = player.getLevel();;
        this.nickname = player.getNickname();
        this.freeTier = player.getFreeTier();
        this.soloTier = player.getSoloTier();
    }

    public int getLevel() {
        return level;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFreeTier() {
        return freeTier;
    }

    public String getSoloTier() {
        return soloTier;
    }
}
