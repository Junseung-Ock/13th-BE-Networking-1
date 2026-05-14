package cotato.backend.domain.application.application;

import cotato.backend.api.application.dto.ApplicationDetailResponse;
import cotato.backend.api.application.dto.ApplicationListResponse;
import cotato.backend.api.application.dto.ApplicationRequest;
import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.applicant.dao.ApplicantRepository;
import cotato.backend.domain.applicant.entity.Applicant;
import cotato.backend.domain.application.dao.ApplicationRepository;
import cotato.backend.domain.application.entity.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicantRepository applicantRepository;

    @Transactional
    public ApplicationDetailResponse registerApplication(ApplicationRequest request) {
        Applicant applicant = applicantRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseGet(() -> applicantRepository.save(
                        Applicant.builder()
                                .name(request.getName())
                                .age(request.getAge())
                                .phoneNumber(request.getPhoneNumber())
                                .build()
                ));

        if (applicationRepository.existsByApplicantAndPeriod(applicant, request.getPeriod())) {
            throw new AppException(ErrorCode.DUPLICATE_APPLICATION);
        }

        Application application = applicationRepository.save(Application.builder()
                .applicant(applicant)
                .period(request.getPeriod())
                .part(request.getPart())
                .ability(request.getAbility())
                .passion(request.getPassion())
                .applicationTime(request.getApplicationTime())
                .build());

        return ApplicationDetailResponse.from(application);
    }

    public ApplicationDetailResponse getApplicationById(Long id) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        return ApplicationDetailResponse.from(app);
    }

    public List<ApplicationListResponse> getApplicationList(String filterBy, Integer period, int page, int pageSize) {
        List<Application> applications = switch (filterBy) {
            case "gisu" -> {
                if (period == null || period < 1) throw new AppException(ErrorCode.INVALID_PARAMETER);
                yield applicationRepository.findByPeriod(period, PageRequest.of(page - 1, pageSize)).getContent();
            }
            case "likes" -> applicationRepository
                    .findAllByOrderByLikesCountDesc(PageRequest.of(page - 1, pageSize))
                    .getContent();
            case "gisu+likes" -> {
                if (period == null || period < 1) throw new AppException(ErrorCode.INVALID_PARAMETER);
                yield applicationRepository.findByPeriodOrderByLikesCountDesc(period, PageRequest.of(page - 1, pageSize)).getContent();
            }
            default -> throw new AppException(ErrorCode.INVALID_FILTER);
        };

        return applications.stream()
                .map(ApplicationListResponse::from)
                .toList();
    }
}
