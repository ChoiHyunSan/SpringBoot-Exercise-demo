package SpringBoot_Exercise.demo.repository;

import SpringBoot_Exercise.demo.domain.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByEmail(String email);
    Optional<SiteUser> findByName(String username);
}
