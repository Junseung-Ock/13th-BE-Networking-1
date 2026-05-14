package cotato.backend.api.application.dto;

import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.entity.Part;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationDetailResponse {
    private Long id;
    private String name;
    private Integer period;
    private Integer age;
    private Part part;
    private Integer ability;
    private Integer passion;
    private String phoneNumber;
    private String applicationTime;

    public static ApplicationDetailResponse from(Application application) {
        return ApplicationDetailResponse.builder()
                .id(application.getId())
                .name(application.getApplicant().getName())
                .period(application.getPeriod())
                .age(application.getApplicant().getAge())
                .part(application.getPart())
                .ability(application.getAbility())
                .passion(application.getPassion())
                .phoneNumber(application.getApplicant().getPhoneNumber())
                .applicationTime(application.getApplicationTime())
                .build();
    }
}
