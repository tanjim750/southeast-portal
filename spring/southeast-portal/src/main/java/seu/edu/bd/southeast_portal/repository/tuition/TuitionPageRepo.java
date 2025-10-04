package seu.edu.bd.southeast_portal.repository.tuition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.tuition.TuitionPage;

@Repository
public interface TuitionPageRepo extends JpaRepository<TuitionPage, Long> {
//    TuitionFeePage findTuitionById(Integer id);
}
