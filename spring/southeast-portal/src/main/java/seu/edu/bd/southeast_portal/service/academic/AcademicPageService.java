package seu.edu.bd.southeast_portal.service.academic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import seu.edu.bd.southeast_portal.model.academics.AcademicsPage;
import seu.edu.bd.southeast_portal.repository.academic.AcademicPageRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AcademicPageService {
    @Autowired
    private AcademicPageRepo repo;

    public ResponseEntity<?> getAll(){
        Map<String, Object> response = new HashMap<>();
        List<AcademicsPage> academicsPage = repo.findAll();
        if (academicsPage.isEmpty()) {
            response.put("status", "failed");
            response.put("message","No records found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(academicsPage);
    }

    public ResponseEntity<?> getById(Long id){
        Map<String, Object> response = new HashMap<>();
        AcademicsPage academicsPage = repo.findById(id).orElse(null);
        if (academicsPage == null) {
            response.put("status","failed");
            response.put("error","Footer id not found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(academicsPage);
    }

    public ResponseEntity<?> add(AcademicsPage academicsPage){
        Map<String, Object> response = new HashMap<>();
        if(academicsPage.isValidFields()){
            return ResponseEntity.ok(repo.save(academicsPage));
        }else {
            response.put("status","failed");
            response.put("error","Missing required fields");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> update(AcademicsPage academicsPage,Long id){
        Map<String, Object> response = new HashMap<>();
        AcademicsPage getacademicsPage = repo.findById(id).orElse(null);

        if (getacademicsPage == null) {
            response.put("status","failed");
            response.put("error","Requested id not found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        if (academicsPage.isValidFields()){
            academicsPage.setId(id);
            response.put("status","success");
            response.put("message","Successfully updated");
            response.put("update",repo.save(academicsPage));
            return ResponseEntity.ok(response);
        }else{
            response.put("status","failed");
            response.put("error","Missing required fields");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> delete(Long id){
        Map<String, Object> response = new HashMap<>();

        AcademicsPage academicsPage = repo.findById(id).orElse(null);
        if (academicsPage == null) {
            response.put("status","failed");
            response.put("error","Requested id not found");
            System.out.println(response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {
            repo.delete(academicsPage);
            response.put("status", "success");
            response.put("message", "Successfully deleted");
            return ResponseEntity.ok(response);
        }
    }
}
