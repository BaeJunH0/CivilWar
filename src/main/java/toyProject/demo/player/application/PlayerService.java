package toyProject.demo.player.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.demo.player.application.dto.PlayerCommand;
import toyProject.demo.player.domain.Player;
import toyProject.demo.player.domain.TeamPlayer;
import toyProject.demo.player.persistent.PlayerRepository;
import toyProject.demo.player.persistent.TeamPlayerRepository;
import toyProject.demo.player.presentation.dto.PlayerRequest;
import toyProject.demo.riot.RiotClient;
import toyProject.demo.riot.dto.AccountDTO;
import toyProject.demo.riot.dto.LeagueEntryDTO;
import toyProject.demo.team.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamPlayerRepository teamPlayerRepository;
    private final RiotClient riotClient;

    @Transactional(readOnly = true)
    public List<LeagueEntryDTO> searchPlayer(PlayerCommand playerCommand) {
        AccountDTO accountDTO = riotClient.getPlayerDataByNicknameAndTag(
                playerCommand.nickname(),
                playerCommand.tag()
        );

        String summonerId = riotClient.getSummonerIdByPuuid(accountDTO.puuid());
        return riotClient.getTierInfoBySummonerId(summonerId);
    }

    @Transactional
    public List<Player> reqToEnt(List<PlayerRequest> playerRequests) {
        List<Player> players = new ArrayList<>();
        for (PlayerRequest playerRequest : playerRequests) {
            // 플레이어 정보 검색 (puuid, nickname, tag)
            AccountDTO accountDto = riotClient.getPlayerDataByNicknameAndTag(
                    playerRequest.nickname(), playerRequest.tag()
            );

            // 저장된 puuid면 가져오고, 아니면 생성
            Player player = playerRepository.findByPuuid(accountDto.puuid()).orElseGet(
                    () -> playerRepository.save(
                            Player.of(accountDto.puuid(), accountDto.gameName(), accountDto.tagLine())
                    )
            );

            // 플레이어 티어 검색
            if(player.getFreeTier() == null && player.getSoloTier() == null) {
                String summonerId = riotClient.getSummonerIdByPuuid(player.getPuuid());
                List<LeagueEntryDTO> leagueEntries = riotClient.getTierInfoBySummonerId(summonerId);
                String soloTier = null;
                String freeTier = null;
                for (LeagueEntryDTO leagueEntry : leagueEntries) {
                    if(leagueEntry.queueType().equals("RANKED_SOLO_5x5")) {
                        soloTier = leagueEntry.tier() + " " + leagueEntry.rank();
                    }

                    if(leagueEntry.queueType().equals("RANKED_FLEX_SR")) {
                        freeTier = leagueEntry.tier() + " " + leagueEntry.rank();
                    }
                }
                player.addTierInfo(soloTier, freeTier);
            }
            players.add(player);
        }

        return players;
    }

    @Transactional
    public void playerJoinToTeam(Team team, Player player) {
        TeamPlayer teamPlayer = TeamPlayer.of(team, player);
        teamPlayerRepository.save(teamPlayer);
    }
}
