package cotato.backend.domain.application.dao;

import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.application.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // 중복 지원 체크
    boolean existsByApplicantAndPeriod(Applicant applicant, Integer period);

    // 기수별 조회
    Page<Application> findByPeriod(Integer period, Pageable pageable);

    // 좋아요 순 조회
    Page<Application> findAllByOrderByLikesCountDesc(Pageable pageable);

    // 기수별 + 좋아요 순 조회
    Page<Application> findByPeriodOrderByLikesCountDesc(Integer period, Pageable pageable);

}
