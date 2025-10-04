package seu.edu.bd.southeast_portal.service.about;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import seu.edu.bd.southeast_portal.model.about.About;
import seu.edu.bd.southeast_portal.repository.about.AboutRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AboutService {
    @Autowired
    private AboutRepo repo;

    public ResponseEntity<?> getAll(){
        Map<String, Object> response = new HashMap<>();
        List<About> about = repo.findAll();
        if (about.isEmpty()) {
            response.put("status", "failed");
            response.put("message","No records found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(about);
    }

    public ResponseEntity<?> getById(Long id){
        Map<String, Object> response = new HashMap<>();
        About about = repo.findById(id).orElse(null);
        if (about == null) {
            response.put("status","failed");
            response.put("error","Footer id not found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(about);
    }

    public ResponseEntity<?> addAbout(About about){
        Map<String, Object> response = new HashMap<>();
        if(about.isValidFields()){
            return ResponseEntity.ok(repo.save(about));
        }else {
            response.put("status","failed");
            response.put("error","Missing required fields");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> updateAbout(About about,Long id){
        Map<String, Object> response = new HashMap<>();
        About getAbout = repo.findById(id).orElse(null);

        if (getAbout == null) {
            response.put("status","failed");
            response.put("error","Requested id not found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        if (about.isValidFields()){
            about.setId(id);
            response.put("status","success");
            response.put("message","Successfully updated");
            response.put("update",repo.save(about));
            return ResponseEntity.ok(response);
        }else{
            response.put("status","failed");
            response.put("error","Missing required fields");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> deleteAbout(Long id){
        Map<String, Object> response = new HashMap<>();

        About about = repo.findById(id).orElse(null);
        if (about == null) {
            response.put("status","failed");
            response.put("error","Requested id not found");
            System.out.println(response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {
            repo.delete(about);
            response.put("status", "success");
            response.put("message", "Successfully deleted");
            return ResponseEntity.ok(response);
        }
    }
}
