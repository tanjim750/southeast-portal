package seu.edu.bd.southeast_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
