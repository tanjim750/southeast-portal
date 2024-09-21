package seu.edu.bd.southeast_portal.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seu.edu.bd.southeast_portal.model.footer.Footer;
import seu.edu.bd.southeast_portal.service.footer.FooterService;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/footer")
public class FooterController {
    @Autowired
    private FooterService service;

    @GetMapping
    public ResponseEntity<?> getFooter(HttpServletRequest request) {
        String id = request.getParameter("id");

        if(id == null || id.isBlank()) {
            return service.getAllFooters();
        }else {
            return service.getFooterById(Long.parseLong(id));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFooterByIdPath(@PathVariable Long id) {
        return service.getFooterById(id);
    }

    @PostMapping
    public ResponseEntity<?> postFooter(@RequestBody Footer footer) {
        return service.saveFooter(footer);

    }

    @PutMapping
    public String putFooter() {
        return "put footer";
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFooter(HttpServletRequest request) {
        return service.deleteFooterById(request.getParameter("id"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFooterByPath(@PathVariable String id) {
        return service.deleteFooterById(id);
    }
}
