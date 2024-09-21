package seu.edu.bd.southeast_portal.repository.footer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.footer.SocialMediaLinks;

@Repository
public interface SocialMediaLinksRepo extends JpaRepository<SocialMediaLinks, Long> {
    SocialMediaLinks findById(long id);
}

