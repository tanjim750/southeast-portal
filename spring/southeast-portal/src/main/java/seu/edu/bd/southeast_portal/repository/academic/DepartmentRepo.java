package seu.edu.bd.southeast_portal.repository.academic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seu.edu.bd.southeast_portal.model.academics.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
//    Department getDepartmentById(Integer id);
}
