package toyProject.demo.riot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import toyProject.demo.riot.dto.AccountDTO;
import toyProject.demo.riot.dto.LeagueEntryDTO;
import toyProject.demo.riot.dto.SummonerDTO;

import java.util.List;

@Component
public class RiotClient {
    private final WebClient asiaClient;
    private final WebClient krClient;

    public RiotClient(@Value("${API_KEY}") String apiKey) {
        this.asiaClient = WebClient.builder()
                .baseUrl("https://asia.api.riotgames.com")
                .defaultHeader("X-Riot-Token", apiKey)
                .build();
        this.krClient = WebClient.builder()
                .baseUrl("https://kr.api.riotgames.com")
                .defaultHeader("X-Riot-Token", apiKey)
                .build();
    }

    public AccountDTO getPlayerDataByNicknameAndTag(String nickname, String tag) {
        return asiaClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/riot/account/v1/accounts/by-riot-id/{nickname}/{tag}")
                        .build(nickname, tag))
                .retrieve()
                .bodyToMono(AccountDTO.class)
                .block();
    }

    public String getSummonerIdByPuuid(String puuid) {
        return krClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/lol/summoner/v4/summoners/by-puuid/{puuid}")
                        .build(puuid))
                .retrieve()
                .bodyToMono(SummonerDTO.class)
                .block()
                .id();
    }

    public List<LeagueEntryDTO> getTierInfoBySummonerId(String summonerId) {
        return krClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/lol/league/v4/entries/by-summoner/{summonerId}")
                        .build(summonerId))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<LeagueEntryDTO>>() {})
                .block();
    }
}
