package seu.edu.bd.southeast_portal.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seu.edu.bd.southeast_portal.model.about.About;
import seu.edu.bd.southeast_portal.service.about.AboutService;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/page/about")
public class AboutController {
    @Autowired
    private AboutService service;

    @GetMapping("/get")
    public ResponseEntity<?> get(HttpServletRequest request){
        String id = request.getParameter("id");

        if(id == null || id.isBlank()) {
            return service.getAll();
        }else {
            return service.getById(Long.parseLong(id));
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        if(id == null || id.isBlank()) {
            Map<String,Object> response = new HashMap<>();
            response.put("status","failed");
            response.put("message","Id is required");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {
            return service.getById(Long.parseLong(id));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody About about){
        return service.addAbout(about);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody About about, @PathVariable String id){

        if(id == null || id.isBlank()) {
            Map<String,Object> response = new HashMap<>();
            response.put("status","failed");
            response.put("message","Id is required");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {
            return service.updateAbout(about, Long.parseLong(id));
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        if(id == null || id.isBlank()) {
            Map<String,Object> response = new HashMap<>();
            response.put("status","failed");
            response.put("message","Id is required");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {
            return service.deleteAbout(Long.parseLong(id));
        }
    }
}
