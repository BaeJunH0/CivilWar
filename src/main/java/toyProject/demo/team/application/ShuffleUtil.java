package toyProject.demo.team.application;

import toyProject.demo.player.application.dto.PlayerInfo;

import java.util.*;

public class ShuffleUtil {
    private static final Map<PlayerInfo, Integer> playerScore = new HashMap<>();

    private static Integer calculation(PlayerInfo playerInfo) {
        int soloScore = 0;
        int freeScore = 0;

        if(playerInfo.soloTier() != null) {
            String[] soloTier = playerInfo.soloTier().split(" ");
            soloScore = Score.fromName(soloTier[0]) * 400 + (4 - Score.fromName(soloTier[1])) * 100;
        }

        if(playerInfo.freeTier() != null) {
            String[] freeTier = playerInfo.freeTier().split(" ");
            freeScore = Score.fromName(freeTier[0]) * 400 + (4 - Score.fromName(freeTier[1])) * 100;
        }

        if(soloScore == 0 && freeScore != 0) {
            return freeScore;
        }
        if(freeScore == 0 && soloScore != 0) {
            return soloScore;
        }

        return (int) Math.round(soloScore * 0.7 + freeScore * 0.3);
    }

    public static List<PlayerInfo> shuffle(List<PlayerInfo> playerInfos) {
        for (PlayerInfo playerInfo : playerInfos) {
            playerScore.put(
                    playerInfo,
                    calculation(playerInfo)
            );
        }

        List<Map.Entry<PlayerInfo, Integer>> entryList = new ArrayList<>(playerScore.entrySet());
        playerScore.clear();
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder())); // 값 기준 내림차순 정렬

        return entryList.stream().map(Map.Entry::getKey).toList();
    }
}
