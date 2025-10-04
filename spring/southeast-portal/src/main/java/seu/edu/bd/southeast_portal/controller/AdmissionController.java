package seu.edu.bd.southeast_portal.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seu.edu.bd.southeast_portal.model.academics.Department;
import seu.edu.bd.southeast_portal.model.admission.Admission;
import seu.edu.bd.southeast_portal.service.academic.DepartmentService;
import seu.edu.bd.southeast_portal.service.admission.AdmissionService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admission")
public class AdmissionController {
    @Autowired
    private AdmissionService service;

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
    public ResponseEntity<?> add(@RequestBody Admission body){
        return service.add(body);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Admission body, @PathVariable String id){

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
