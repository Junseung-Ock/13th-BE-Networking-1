package cotato.backend.api.staff.dto;

import cotato.backend.domain.staff.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StaffResponse {
    private Long id;
    private String name;
    private Integer age;
    private String phoneNumber;
    private Role role;
}
