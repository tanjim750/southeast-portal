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
@RequestMapping("/api/v1/page/footer")
public class FooterController {
    @Autowired
    private FooterService service;

    @GetMapping("/get")
    public ResponseEntity<?> getFooter(HttpServletRequest request) {
        String id = request.getParameter("id");

        if(id == null || id.isBlank()) {
            return service.getAllFooters();
        }else {
            return service.getFooterById(Long.parseLong(id));
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return service.getFooterById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> postFooter(@RequestBody Footer footer) {
        return service.saveFooter(footer);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Footer footer) {
        return service.updateFooterById(id, footer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return service.deleteFooterById(id);
    }
}
