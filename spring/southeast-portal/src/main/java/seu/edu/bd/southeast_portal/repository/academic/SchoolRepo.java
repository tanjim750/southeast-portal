package seu.edu.bd.southeast_portal.repository.academic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.academics.School;

@Repository
public interface SchoolRepo extends JpaRepository<School, Long> {
//    School findById(int id);
}
