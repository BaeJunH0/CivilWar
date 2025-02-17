package toyProject.demo.riot.dto;

public record SummonerDTO(
        String accountId,
        int profileIconId,
        Long revisionDate,
        String id,
        String puuid,
        Long summonerLevel
) {
}
