package cotato.backend.api.applicant.dto;

import lombok.Getter;

@Getter
public class ApplicantRequest {
    private String name;
    private Integer age;
    private String phoneNumber;
}