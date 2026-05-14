package cotato.backend.api.staff;

import cotato.backend.api.staff.dto.StaffRequest;
import cotato.backend.api.staff.dto.StaffResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.staff.application.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/staffs")
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<DataResponse<Void>> register(@RequestBody @Valid StaffRequest request) {
        staffService.registerStaff(request);
        return ResponseEntity.ok(DataResponse.ok());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<StaffResponse>> getStaff(@PathVariable Long id) {
        return ResponseEntity.ok(DataResponse.from(staffService.getStaffById(id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> update(
            @PathVariable Long id,
            @RequestBody @Valid StaffRequest request) {
        staffService.updateStaff(id, request);
        return ResponseEntity.ok(DataResponse.ok());
    }
}
