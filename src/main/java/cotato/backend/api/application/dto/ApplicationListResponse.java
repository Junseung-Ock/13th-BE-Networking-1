package cotato.backend.api.application.dto;

import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.entity.Part;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationListResponse {
    private Long id;
    private String name;
    private Integer period;
    private Part part;
    private Integer likesCount;

    public static ApplicationListResponse from(Application application) {
        return ApplicationListResponse.builder()
                .id(application.getId())
                .name(application.getApplicant().getName())
                .period(application.getPeriod())
                .part(application.getPart())
                .likesCount(application.getLikesCount())
                .build();
    }
}
