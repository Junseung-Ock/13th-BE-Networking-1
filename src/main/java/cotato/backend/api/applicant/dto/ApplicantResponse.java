package cotato.backend.api.applicant.dto;

import cotato.backend.domain.applicant.entity.Applicant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicantResponse {
    private Long id;
    private String name;
    private Integer age;
    private String phoneNumber;

    public static ApplicantResponse from(Applicant applicant) {
        return ApplicantResponse.builder()
                .id(applicant.getId())
                .name(applicant.getName())
                .age(applicant.getAge())
                .phoneNumber(applicant.getPhoneNumber())
                .build();
    }
}
