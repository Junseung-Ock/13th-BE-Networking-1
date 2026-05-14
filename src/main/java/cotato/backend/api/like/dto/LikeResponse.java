package cotato.backend.api.like.dto;

import cotato.backend.domain.like.entity.Like;
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

    public static LikeResponse from(Like like) {
        return LikeResponse.builder()
                .id(like.getId())
                .staffId(like.getStaff().getId())
                .staffName(like.getStaff().getName())
                .applicationId(like.getApplication().getId())
                .applicantName(like.getApplication().getApplicant().getName())
                .build();
    }
}