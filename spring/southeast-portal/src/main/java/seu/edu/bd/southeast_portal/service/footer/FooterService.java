package seu.edu.bd.southeast_portal.service.footer;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import seu.edu.bd.southeast_portal.model.footer.Footer;
import seu.edu.bd.southeast_portal.repository.footer.FooterRepo;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class FooterService {
    @Autowired
    FooterRepo repo;

    public ResponseEntity<?> saveFooter( Footer footer) {
        System.out.println(footer);
        Map<String,String> response = new HashMap<>();
        try{
            return ResponseEntity.ok(repo.save(footer));
        }catch (ConstraintViolationException e){
            e.printStackTrace();
            response.put("status","failed");
            response.put("message","Required parameter is missing");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (DataIntegrityViolationException e){
            response.put("status","failed");
            response.put("message","Required parameter is missing");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (Exception e){
            response.put("status","failed");
            response.put("message","Something went wrong");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    public ResponseEntity<?> getAllFooters() {
        return ResponseEntity.ok(repo.findAll());
    }

    public ResponseEntity<?> getFooterById(Long id) {
        Map<String,String> response = new HashMap<>();

        Footer footer = repo.findById(id).orElse(null);

        if (footer == null) {
            response.put("status","failed");
            response.put("message","Footer id not found");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(footer);
    }

    public ResponseEntity<?> deleteFooterById(String id) {
        Map<String,String> response = new HashMap<>();

        if(id == null || id.isBlank()){
            response.put("status","failed");
            response.put("message","Id is required");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        Long longId = Long.parseLong(id);

        Footer footer = repo.findById(longId).orElse(null);

        if (footer == null) {
            response.put("status","failed");
            response.put("message","Footer id not found");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }else {
            repo.delete(footer);
            response.put("status","success");
            response.put("message","Footer deleted successfully");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }
}
