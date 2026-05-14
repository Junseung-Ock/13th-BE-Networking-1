package cotato.backend.api.staff;

import cotato.backend.api.staff.dto.StaffRequest;
import cotato.backend.api.staff.dto.StaffResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.staff.application.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/staffs")
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<DataResponse<StaffResponse>> register(@RequestBody @Valid StaffRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(DataResponse.created(staffService.registerStaff(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<StaffResponse>> getStaff(@PathVariable Long id) {
        return ResponseEntity.ok(DataResponse.from(staffService.getStaffById(id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DataResponse<StaffResponse>> update(
            @PathVariable Long id,
            @RequestBody @Valid StaffRequest request) {
        return ResponseEntity.ok(DataResponse.from(staffService.updateStaff(id, request)));
    }
}
