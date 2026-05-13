package cotato.backend.api.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationDetailResponse {
    private Long id;
    private String name;
    private Integer period;
    private Integer age;
    private String part;
    private Integer ability;
    private Integer passion;
    private String phoneNumber;
    private String applicationTime;
}
