package toyProject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.demo.domain.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Override
    List<Player> findAll();

    @Override
    Optional<Player> findById(Long id);

    @Override
    <S extends Player> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
