package cotato.backend.api.applicant.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicantResponse {
    private Long id;
    private String name;
    private Integer age;
    private String phoneNumber;
}
