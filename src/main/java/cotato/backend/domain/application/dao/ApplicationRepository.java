package cotato.backend.domain.application.dao;

import cotato.backend.domain.application.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // 기수별 필터링
    Page<Application> findByPeriod(Integer period, Pageable pageable);

}
