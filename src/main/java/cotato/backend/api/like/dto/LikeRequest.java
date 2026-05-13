package cotato.backend.api.like.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LikeRequest {

    @NotNull(message = "운영진 ID는 필수입니다")
    private Long staffId;

    @NotNull(message = "서류 ID는 필수입니다")
    private Long applicationId;
}
