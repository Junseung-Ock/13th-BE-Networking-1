package cotato.backend.domain.like.dao;

import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.like.entity.Like;
import cotato.backend.domain.staff.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByApplicationAndStaff(Application application, Staff staff);
    Optional<Like> findByApplicationAndStaff(Application application, Staff staff);
    List<Like> findByApplication(Application application);
}