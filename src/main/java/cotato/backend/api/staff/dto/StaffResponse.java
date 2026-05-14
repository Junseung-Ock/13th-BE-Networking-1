package cotato.backend.api.staff.dto;

import cotato.backend.domain.staff.entity.Role;
import cotato.backend.domain.staff.entity.Staff;
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

    public static StaffResponse from(Staff staff) {
        return StaffResponse.builder()
                .id(staff.getId())
                .name(staff.getName())
                .age(staff.getAge())
                .phoneNumber(staff.getPhoneNumber())
                .role(staff.getRole())
                .build();
    }
}
