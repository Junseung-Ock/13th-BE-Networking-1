package cotato.backend.api.application.dto;

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
}
