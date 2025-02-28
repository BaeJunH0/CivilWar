package toyProject.demo.player.presentation.dto;

public class PlayerRequest {
    public record Search(String nickname, String tag) {

    }

    public record Save(String nickname, String tag, String soloRank, String freeRank) {

    }
}
