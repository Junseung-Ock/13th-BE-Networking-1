package cotato.backend.api.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationListResponse {
    private Long id;
    private String name;
    private Integer period;
    private String part;
    private Integer likesCount;
}
