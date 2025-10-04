package seu.edu.bd.southeast_portal.service.tuition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import seu.edu.bd.southeast_portal.model.academics.Department;
import seu.edu.bd.southeast_portal.model.tuition.ScholarshipPage;
import seu.edu.bd.southeast_portal.repository.academic.DepartmentRepo;
import seu.edu.bd.southeast_portal.repository.tuition.ScholarshipPageRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScholarshipPageService {
    @Autowired
    private ScholarshipPageRepo repo;

    public ResponseEntity<?> getAll(){
        Map<String, Object> response = new HashMap<>();
        List<ScholarshipPage> all = repo.findAll();
        if (all.isEmpty()) {
            response.put("status", "failed");
            response.put("message","No records found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<?> getById(Long id){
        Map<String, Object> response = new HashMap<>();
        ScholarshipPage getInfo = repo.findById(id).orElse(null);
        if (getInfo == null) {
            response.put("status","failed");
            response.put("error","Footer id not found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(getInfo);
    }

    public ResponseEntity<?> add(ScholarshipPage info){
        Map<String, Object> response = new HashMap<>();
        if(info.isValidFields()){
            return ResponseEntity.ok(repo.save(info));
        }else {
            response.put("status","failed");
            response.put("error","Missing required fields");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> update(ScholarshipPage info,Long id){
        Map<String, Object> response = new HashMap<>();
        ScholarshipPage getInfo = repo.findById(id).orElse(null);

        if (getInfo == null) {
            response.put("status","failed");
            response.put("error","Requested id not found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        if (info.isValidFields()){
            info.setId(id);
            response.put("status","success");
            response.put("message","Successfully updated");
            response.put("update",repo.save(info));
            return ResponseEntity.ok(response);
        }else{
            response.put("status","failed");
            response.put("error","Missing required fields");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> delete(Long id){
        Map<String, Object> response = new HashMap<>();

        ScholarshipPage getInfo = repo.findById(id).orElse(null);
        if (getInfo == null) {
            response.put("status","failed");
            response.put("error","Requested id not found");
            System.out.println(response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {
            repo.delete(getInfo);
            response.put("status", "success");
            response.put("message", "Successfully deleted");
            return ResponseEntity.ok(response);
        }
    }
}
