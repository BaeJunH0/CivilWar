package toyProject.demo.player.persistent;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.demo.player.domain.Player;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByPuuid(String puuid);
}
