package seu.edu.bd.southeast_portal.repository.academic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.academics.AcademicsPage;

@Repository
public interface AcademicPageRepo extends JpaRepository<AcademicsPage,Long> {
//    AcademicsPage findByAcademicPageId(Integer academicPageId);
}
