package seu.edu.bd.southeast_portal.repository.footer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.footer.MenuLinks;

@Repository
public interface MenuLinksRepo extends JpaRepository<MenuLinks,Long> {
    MenuLinks findById(long id);
}
