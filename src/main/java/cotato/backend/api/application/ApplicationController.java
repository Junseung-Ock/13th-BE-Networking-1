package cotato.backend.api.application;

import cotato.backend.api.application.dto.ApplicationDetailResponse;
import cotato.backend.api.application.dto.ApplicationListRequest;
import cotato.backend.api.application.dto.ApplicationListResponse;
import cotato.backend.api.application.dto.ApplicationRequest;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.application.application.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<DataResponse<List<ApplicationListResponse>>> getList(
            @Valid @ModelAttribute ApplicationListRequest request) {
        return ResponseEntity.ok(DataResponse.from(applicationService.getApplicationList(request)));
    }
}
