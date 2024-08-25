package toyProject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
