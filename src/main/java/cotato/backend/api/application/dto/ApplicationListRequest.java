package cotato.backend.api.application.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationListRequest {
    private String filterBy;  // "likes", "gisu", "gisu+likes"
    private int period;

    @Min(value = 1, message = "페이지는 1 이상이어야 합니다")
    private int page;
}
