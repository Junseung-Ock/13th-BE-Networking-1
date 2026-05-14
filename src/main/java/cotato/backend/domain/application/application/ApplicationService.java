package cotato.backend.domain.application.application;

import cotato.backend.api.application.dto.ApplicationDetailResponse;
import cotato.backend.api.application.dto.ApplicationListRequest;
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
import org.springframework.data.domain.Pageable;
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

    public List<ApplicationListResponse> getApplicationList(ApplicationListRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, 10);

        List<Application> applications = switch (request.getFilterBy()) {
            case "gisu" -> {
                if (request.getPeriod() < 1) throw new AppException(ErrorCode.INVALID_PARAMETER);
                yield applicationRepository.findByPeriod(request.getPeriod(), pageable).getContent();
            }
            case "likes" -> applicationRepository
                    .findAllByOrderByLikesCountDesc(PageRequest.of(0, 10))
                    .getContent();
            case "gisu+likes" -> {
                if (request.getPeriod() < 1) throw new AppException(ErrorCode.INVALID_PARAMETER);
                yield applicationRepository.findByPeriodOrderByLikesCountDesc(request.getPeriod(), pageable).getContent();
            }
            default -> throw new AppException(ErrorCode.INVALID_FILTER);
        };

        return applications.stream()
                .map(ApplicationListResponse::from)
                .toList();
    }
}