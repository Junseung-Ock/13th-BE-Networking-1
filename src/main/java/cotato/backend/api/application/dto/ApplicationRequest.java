package cotato.backend.api.application.dto;

import cotato.backend.domain.application.entity.Part;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class ApplicationRequest {

    @NotBlank(message = "이름은 필수입니다")
    @Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 한글 2~10자여야 합니다")
    private String name;

    @NotNull
    @Min(value = 1, message = "기수는 1 이상이어야 합니다")
    private Integer period;

    @NotNull(message = "나이는 필수 입력값입니다")
    @Min(value = 22, message = "나이는 22살 이상이어야 합니다")
    @Max(value = 30, message = "나이는 30살 이하여야 합니다")
    private Integer age;

    @NotNull(message = "지원 파트는 필수 입력값입니다")
    private Part part;

    @NotNull(message = "실력은 필수 입력값입니다")
    @Min(value = 0, message = "실력은 0 이상이어야 합니다")
    @Max(value = 10, message = "실력은 10 이하여야 합니다")
    private Integer ability;

    @NotNull(message = "열정은 필수 입력값입니다")
    @Min(value = 0, message = "열정은 0 이상이어야 합니다")
    @Max(value = 10, message = "열정은 10 이하여야 합니다")
    private Integer passion;

    @NotBlank(message = "전화번호는 필수 입력값입니다")
    @Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리여야 합니다")
    private String phoneNumber;

    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$", message = "서류 제출 시간은 yyyy-MM-dd HH:mm 형식이어야 합니다")
    private String applicationTime;
}
