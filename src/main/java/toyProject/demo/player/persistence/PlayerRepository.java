package toyProject.demo.player.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.demo.player.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
