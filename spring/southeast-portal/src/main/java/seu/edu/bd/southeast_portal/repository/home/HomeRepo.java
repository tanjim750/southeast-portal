package seu.edu.bd.southeast_portal.repository.home;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.home.Home;

@Repository
public interface HomeRepo extends JpaRepository<Home, Long> {
}
