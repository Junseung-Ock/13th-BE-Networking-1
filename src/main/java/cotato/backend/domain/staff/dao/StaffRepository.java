package cotato.backend.domain.staff.dao;

import cotato.backend.domain.staff.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {}
