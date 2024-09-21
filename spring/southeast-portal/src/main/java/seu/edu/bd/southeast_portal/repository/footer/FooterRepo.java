package seu.edu.bd.southeast_portal.repository.footer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.footer.Footer;

@Repository
public interface FooterRepo extends JpaRepository<Footer, Long> {
    Footer findById(long id);
}
