package toyProject.demo.player.application;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.demo.exception.CustomException;
import toyProject.demo.exception.errorCode.PlayerErrorCode;
import toyProject.demo.player.application.dto.PlayerCommand;
import toyProject.demo.player.application.dto.PlayerInfo;
import toyProject.demo.player.domain.Player;
import toyProject.demo.player.domain.TeamPlayer;
import toyProject.demo.player.persistent.PlayerRepository;
import toyProject.demo.player.persistent.TeamPlayerRepository;
import toyProject.demo.player.presentation.dto.PlayerRequest;
import toyProject.demo.riot.RiotClient;
import toyProject.demo.riot.dto.AccountDTO;
import toyProject.demo.riot.dto.LeagueEntryDTO;
import toyProject.demo.team.domain.Team;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamPlayerRepository teamPlayerRepository;
    private final RiotClient riotClient;
    private final List<PlayerInfo> defaultPlayerList = new ArrayList<>(10);

    @PostConstruct
    public void init() {
        for(int i = 0; i < 10; i++) {
            defaultPlayerList.add(PlayerInfo.of(
                    Player.of(" ", "defaultPlayer" + i, " ")
            ));
        }
    }

    // 검색 & 플레이어 정보 캐싱
    @Transactional
    public PlayerInfo searchPlayer(PlayerCommand playerCommand) {
        AccountDTO accountDTO = riotClient.getPlayerDataByNicknameAndTag(
                playerCommand.nickname(),
                playerCommand.tag()
        );

        Player player = playerRepository.findPlayerByNicknameAndTag(
                accountDTO.gameName(),
                accountDTO.tagLine()
        ).orElseGet(
                () -> playerRepository.save(Player.of(accountDTO.puuid(), accountDTO.gameName(), accountDTO.tagLine()))
        );

        if(player.getSoloTier() == null && player.getFreeTier() == null) {
            String summonerId = riotClient.getSummonerIdByPuuid(player.getPuuid());
            List<LeagueEntryDTO> leagueEntries = riotClient.getTierInfoBySummonerId(summonerId);
            String soloTier = null;
            String freeTier = null;
            for (LeagueEntryDTO leagueEntry : leagueEntries) {
                if (leagueEntry.queueType().equals("RANKED_SOLO_5x5")) {
                    soloTier = leagueEntry.tier() + " " + leagueEntry.rank();
                }

                if (leagueEntry.queueType().equals("RANKED_FLEX_SR")) {
                    freeTier = leagueEntry.tier() + " " + leagueEntry.rank();
                }
            }
            player.addTierInfo(soloTier, freeTier);
        }
        return PlayerInfo.of(player);
    }

    @Transactional(readOnly = true)
    public Map<Integer, Player> reqToEnt(List<PlayerRequest.Save> playerRequests) {
        Map<Integer, Player> players = new HashMap<>();
        int index = 0;
        for (PlayerRequest.Save playerRequest : playerRequests) {
            Optional<Player> player = playerRepository.findPlayerByNicknameAndTag(
                    playerRequest.nickname(),
                    playerRequest.tag()
            );
            if(player.isPresent()) {
                players.put(index, player.get());

            }
            index++;
        }
        return players;
    }

    @Transactional
    public void playerJoinToTeam(Team team, Player player, int index) {
        TeamPlayer teamPlayer = TeamPlayer.of(team, player, index);
        teamPlayerRepository.save(teamPlayer);
    }
}
