package cotato.backend.domain.like.dao;

import cotato.backend.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByApplicationIdAndStaffId(Long applicationId, Long staffId);
    Optional<Like> findByApplicationIdAndStaffId(Long applicationId, Long staffId);
}