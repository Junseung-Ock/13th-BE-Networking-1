package cotato.backend.api.like.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeResponse {
    private Long id;
    private Long staffId;
    private String staffName;
    private Long applicationId;
    private String applicantName;
}