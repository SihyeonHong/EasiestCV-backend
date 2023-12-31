package EasiestCV.easiestCV.repository;

import EasiestCV.easiestCV.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsById(String userId);
    Optional<User> findByUserid(String userId); // JPA 자동생성
}

