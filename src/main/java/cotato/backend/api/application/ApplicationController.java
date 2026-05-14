package cotato.backend.api.application;

import cotato.backend.api.application.dto.ApplicationDetailResponse;
import cotato.backend.api.application.dto.ApplicationListResponse;
import cotato.backend.api.application.dto.ApplicationRequest;
import java.util.List;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.application.application.ApplicationService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<DataResponse<ApplicationDetailResponse>> register(@RequestBody @Valid ApplicationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(DataResponse.created(applicationService.registerApplication(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ApplicationDetailResponse>> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(DataResponse.from(applicationService.getApplicationById(id)));
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<ApplicationListResponse>>> getApplicationList(
            @Parameter(description = "필터 타입 (likes / gisu / gisu+likes)")
            @RequestParam String filterBy,

            @Parameter(description = "지원 기수 (gisu, gisu+likes 필터 시 필수)")
            @RequestParam(required = false) Integer period,

            @Parameter(description = "페이지 번호")
            @RequestParam(defaultValue = "1") int page,

            @Parameter(description = "페이지 당 건수 (기본값 10)")
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.ok(
                DataResponse.from(applicationService.getApplicationList(filterBy, period, page, pageSize))
        );
    }
}
