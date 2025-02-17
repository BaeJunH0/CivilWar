package toyProject.demo.team.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.demo.team.domain.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findTeamsByOwner(String owner);
}
