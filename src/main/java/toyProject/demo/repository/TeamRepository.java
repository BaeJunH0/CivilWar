package toyProject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.demo.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
