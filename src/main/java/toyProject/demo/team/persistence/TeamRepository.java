package toyProject.demo.team.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.demo.team.domain.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Override
    List<Team> findAll();

    @Override
    Optional<Team> findById(Long id);

    @Override
    <S extends Team> S save(S entity);

    @Override
    void deleteById(Long id);
}
