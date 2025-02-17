package toyProject.demo.player.persistent;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.demo.team.domain.Team;
import toyProject.demo.player.domain.TeamPlayer;

import java.util.List;

public interface TeamPlayerRepository extends JpaRepository<TeamPlayer, Long> {
    List<TeamPlayer> findTeamPlayerByTeam(Team team);
}
