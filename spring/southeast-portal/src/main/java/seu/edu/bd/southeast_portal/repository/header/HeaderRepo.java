package seu.edu.bd.southeast_portal.repository.header;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.header.Header;

@Repository
public interface HeaderRepo extends JpaRepository<Header, Long> {

}
