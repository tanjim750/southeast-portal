package seu.edu.bd.southeast_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import seu.edu.bd.southeast_portal.model.academics.AcademicsPage;
import seu.edu.bd.southeast_portal.service.academic.AcademicPageService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/page/academic")
public class AcademicPageController {
    @Autowired
    private AcademicPageService service;

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
            java.util.Map<String,Object> response = new HashMap<>();
            response.put("status","failed");
            response.put("message","Id is required");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {
            return service.getById(Long.parseLong(id));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AcademicsPage body){
        return service.add(body);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody AcademicsPage body, @PathVariable String id){

        if(id == null || id.isBlank()) {
            java.util.Map<String,Object> response = new HashMap<>();
            response.put("status","failed");
            response.put("message","Id is required");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else {
            return service.update(body, Long.parseLong(id));
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
            return service.delete(Long.parseLong(id));
        }
    }

}
