package seu.edu.bd.southeast_portal.repository.academic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.academics.Scholarship;

@Repository
public interface ScholarshipRepo extends JpaRepository<Scholarship, Long> {
//    Scholarship findByScholarshipId(Integer scholarshipId);
}
