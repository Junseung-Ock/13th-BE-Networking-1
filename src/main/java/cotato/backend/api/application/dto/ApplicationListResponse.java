package cotato.backend.api.application.dto;

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
}
