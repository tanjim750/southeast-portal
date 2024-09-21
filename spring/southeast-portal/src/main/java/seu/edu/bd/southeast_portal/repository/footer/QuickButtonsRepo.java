package seu.edu.bd.southeast_portal.repository.footer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.footer.QuickButtons;

@Repository
public interface QuickButtonsRepo extends JpaRepository<QuickButtons, Long> {
    QuickButtons findById(long id);
}
