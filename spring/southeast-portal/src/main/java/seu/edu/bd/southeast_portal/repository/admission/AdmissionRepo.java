package seu.edu.bd.southeast_portal.repository.admission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.admission.Admission;

@Repository
public interface AdmissionRepo extends JpaRepository<Admission, Long> {
//    Admission findByAdmissionId(Integer admissionId);
}
