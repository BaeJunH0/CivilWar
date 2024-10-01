package toyProject.demo.player.presentation.dto;

public class PlayerRequest {
    private int level;
    private String nickname;
    private String freeTier;
    private String soloTier;

    public PlayerRequest() {}

    public PlayerRequest(int level, String nickname, String freeTier, String soloTier) {
        this.level = level;
        this.nickname = nickname;
        this.freeTier = freeTier;
        this.soloTier = soloTier;
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
