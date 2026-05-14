package cotato.backend.domain.like.application;

import cotato.backend.api.like.dto.LikeRequest;
import cotato.backend.api.like.dto.LikeResponse;
import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.application.dao.ApplicationRepository;
import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.like.dao.LikeRepository;
import cotato.backend.domain.like.entity.Like;
import cotato.backend.domain.staff.dao.StaffRepository;
import cotato.backend.domain.staff.entity.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;
    private final ApplicationRepository applicationRepository;
    private final StaffRepository staffRepository;

    @Transactional
    public LikeResponse like(LikeRequest request) {
        Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        // 이미 좋아요를 눌렀는지 확인
        if (likeRepository.existsByApplicationAndStaff(application, staff)) {
            throw new AppException(ErrorCode.ALREADY_LIKED);
        }

        Like like = likeRepository.save(Like.builder()
                .application(application)
                .staff(staff)
                .build());

        application.increaseLikesCount();

        return LikeResponse.from(like);
    }
}
