package seu.edu.bd.southeast_portal.repository.about;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.about.About;

@Repository
public interface AboutRepo extends JpaRepository<About,Long> {
    About findAboutById(Long id);
}
