package toyProject.demo.team.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.demo.team.domain.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Page<Team> findAll(Pageable pageable);

    List<Team> findTeamsByOwner(String owner);
}
