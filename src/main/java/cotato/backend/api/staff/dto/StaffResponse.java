package cotato.backend.api.staff.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StaffResponse {
    private Long id;
    private String name;
    private Integer age;
    private String phoneNumber;
    private String role;
}
