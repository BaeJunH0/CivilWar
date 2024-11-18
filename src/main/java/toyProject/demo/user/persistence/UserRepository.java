package toyProject.demo.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.demo.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
