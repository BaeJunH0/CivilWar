package toyProject.demo.riot.dto;

public record LeagueEntryDTO(
        String leagueId,
        String queueType,
        String tier,
        String rank,
        String summonerId,
        int leaguePoints,
        int wins,
        int losses,
        boolean veteran,
        boolean inactive,
        boolean freshBlood,
        boolean hotStreak
) {
}
