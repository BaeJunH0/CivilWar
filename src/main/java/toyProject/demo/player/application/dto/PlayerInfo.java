package toyProject.demo.player.application.dto;


import toyProject.demo.player.domain.Player;

public record PlayerInfo(String riotId, String riotTag) {
    public static PlayerInfo from(Player player){
        return new PlayerInfo(player.getRiotId(), player.getRiotTag());
    }
}
