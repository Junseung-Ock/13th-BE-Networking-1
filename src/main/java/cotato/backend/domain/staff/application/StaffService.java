package cotato.backend.domain.staff.application;

import cotato.backend.api.staff.dto.StaffRequest;
import cotato.backend.api.staff.dto.StaffResponse;
import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.staff.dao.StaffRepository;
import cotato.backend.domain.staff.entity.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StaffService {

    private final StaffRepository staffRepository;

    @Transactional
    public Long registerStaff(StaffRequest request) {
        Staff staff = staffRepository.save(Staff.builder()
                .name(request.getName())
                .age(request.getAge())
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .build());
        return staff.getId();
    }

    public StaffResponse getStaffById(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        return StaffResponse.builder()
                .id(staff.getId())
                .name(staff.getName())
                .age(staff.getAge())
                .phoneNumber(staff.getPhoneNumber())
                .role(staff.getRole())
                .build();
    }

    @Transactional
    public void updateStaff(Long id, StaffRequest request) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        staff.update(request.getName(), request.getAge(), request.getPhoneNumber(), request.getRole());
    }
}