package cotato.backend.api.applicant;

import cotato.backend.api.applicant.dto.ApplicantRequest;
import cotato.backend.api.applicant.dto.ApplicantResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.applicant.application.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ApplicantResponse>> getApplicant(@PathVariable Long id) {
        return ResponseEntity.ok(DataResponse.from(applicantService.getApplicantById(id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody ApplicantRequest request) {
        applicantService.updateApplicant(id, request);
        return ResponseEntity.ok(DataResponse.ok());
    }
}