package seu.edu.bd.southeast_portal.repository.tuition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.tuition.ScholarshipPage;

@Repository
public interface ScholarshipPageRepo extends JpaRepository<ScholarshipPage, Long> {
//    ScholarshipPage findByScholarshipId(Integer scholarshipId);
}
