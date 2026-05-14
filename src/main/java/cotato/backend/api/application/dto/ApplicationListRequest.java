package cotato.backend.api.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationListRequest {
    private String filterBy;  // "likes", "gisu", "gisu+likes"
    private int period;
    private int page;
    private int pageSize;
}
