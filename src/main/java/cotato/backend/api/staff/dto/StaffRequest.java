package cotato.backend.api.staff.dto;

import cotato.backend.domain.staff.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class StaffRequest {

    @NotBlank(message = "이름은 필수입니다")
    @Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 한글 2~10자여야 합니다")
    private String name;

    @NotNull(message = "나이는 필수입니다")
    @Min(value = 22, message = "나이는 22살 이상이어야 합니다")
    private Integer age;

    @NotBlank(message = "전화번호는 필수입니다")
    @Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리여야 합니다")
    private String phoneNumber;

    @NotNull(message = "역할은 필수입니다")
    private Role role; // 파트장, 기획팀장, 홍보팀장, 부회장, 회장, 교육팀장
}
