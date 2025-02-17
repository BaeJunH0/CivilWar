package toyProject.demo.member.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.demo.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);

    Optional<Member> findUserByEmail(String email);
}
