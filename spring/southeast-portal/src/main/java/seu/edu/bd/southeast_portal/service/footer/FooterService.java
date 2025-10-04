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
import seu.edu.bd.southeast_portal.model.footer.MenuLinks;
import seu.edu.bd.southeast_portal.model.footer.QuickButtons;
import seu.edu.bd.southeast_portal.model.footer.SocialMediaLinks;
import seu.edu.bd.southeast_portal.repository.footer.FooterRepo;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class FooterService {
    @Autowired
    FooterRepo repo;

    public ResponseEntity<?> saveFooter( Footer footer) {
        System.out.println(footer);
        Map<String,String> response = new HashMap<>();
        try{
            List<SocialMediaLinks> socialMediaLinks = footer.getSocialMediaLinks();
            List<MenuLinks> menuLinks = footer.getMenuLinks();
            List<QuickButtons> quickButtons = footer.getQuickButtons();


            System.out.println(socialMediaLinks);
            System.out.println(menuLinks);
            System.out.println(quickButtons);
            return ResponseEntity.ok(repo.save(footer));
        }catch (ConstraintViolationException e){
            response.put("status","failed");
            response.put("message","Required parameter is missing");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (DataIntegrityViolationException e){
            response.put("status","failed");
            response.put("message","Required parameter is missing");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(footer);
    }

    public ResponseEntity<?> deleteFooterById(String id) {
        Map<String,String> response = new HashMap<>();

        if(id == null || id.isBlank()){
            response.put("status","failed");
            response.put("message","Id is required");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        Long longId = Long.parseLong(id);

        Footer footer = repo.findById(longId).orElse(null);

        if (footer == null) {
            response.put("status","failed");
            response.put("message","Footer id not found");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }else {
            repo.delete(footer);
            response.put("status","success");
            response.put("message","Footer deleted successfully");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    public ResponseEntity<?> updateFooterById(String id, Footer updatedFooter) {
        Map<String,String> response = new HashMap<>();

        if(id == null || id.isBlank()){
            response.put("status","failed");
            response.put("message","Id is required");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        Long longId = Long.parseLong(id);

        Footer footer = repo.findById(longId).orElse(null);

        if (footer == null) {
            response.put("status","failed");
            response.put("message","Footer id not found");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        if (updatedFooter.isFieldsValid()) {
            updatedFooter.setId(longId);
            repo.save(updatedFooter);
            response.put("status","success");
            response.put("message","Footer updated successfully");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else {
            response.put("status","failed");
            response.put("message","Required parameter is missing");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
