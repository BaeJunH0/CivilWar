package toyProject.demo.team.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.demo.exception.CustomException;
import toyProject.demo.exception.errorCode.TeamErrorCode;
import toyProject.demo.player.application.dto.PlayerInfo;
import toyProject.demo.team.application.dto.TeamInfo;
import toyProject.demo.player.domain.TeamPlayer;
import toyProject.demo.player.persistent.TeamPlayerRepository;
import toyProject.demo.team.domain.Team;
import toyProject.demo.team.persistence.TeamRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamPlayerRepository teamPlayerRepository;

    @Transactional(readOnly = true)
    public Page<TeamInfo.Basic> findAll(Pageable pageable) {
        return teamRepository.findAll(pageable).map(TeamInfo.Basic::of);
    }

    @Transactional(readOnly = true)
    public List<TeamInfo.Basic> findMyTeams(String owner) {
        return teamRepository.findTeamsByOwner(owner).stream().map(TeamInfo.Basic::of).toList();
    }

    @Transactional(readOnly = true)
    public TeamInfo.Detail findOneTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(
                () -> CustomException.of(TeamErrorCode.NOT_FOUND)
        );

        List<TeamPlayer> teamPlayers = teamPlayerRepository.findTeamPlayerByTeam(team);
        List<PlayerInfo> playerInfos = teamPlayers.stream()
                .map(TeamPlayer::getPlayer)
                .map(PlayerInfo::of)
                .toList();

        return TeamInfo.Detail.of(playerInfos);
    }

    @Transactional
    public Team save(String name, String owner) {
        return teamRepository.save(Team.of(name, owner));
    }

    @Transactional(readOnly = true)
    public Team findByOwnerAndId(String owner, Long id) {
        Team team = teamRepository.findById(id).orElseThrow(
                () -> CustomException.of(TeamErrorCode.NOT_FOUND)
        );

        if(!team.getOwner().equals(owner)) {
            throw CustomException.of(TeamErrorCode.NOT_MY_TEAM);
        }

        return team;
    }

    @Transactional
    public void cleanTeam(Team team) {
        List<TeamPlayer> teamPlayers = teamPlayerRepository.findTeamPlayerByTeam(team);

        teamPlayerRepository.deleteAll(teamPlayers);
    }

    @Transactional
    public void teamDelete(String owner, Long id){
        Team team = teamRepository.findById(id).orElseThrow(
                () -> CustomException.of(TeamErrorCode.NOT_FOUND)
        );

        if(!team.getOwner().equals(owner)) {
            throw CustomException.of(TeamErrorCode.NOT_MY_TEAM);
        }

        cleanTeam(team);
        teamRepository.deleteById(id);
    }
}
