package toyProject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.demo.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
