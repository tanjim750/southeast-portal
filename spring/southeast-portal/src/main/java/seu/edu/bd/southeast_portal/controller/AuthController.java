package seu.edu.bd.southeast_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import seu.edu.bd.southeast_portal.model.Users;
import seu.edu.bd.southeast_portal.model.UsersRequest;
import seu.edu.bd.southeast_portal.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService service;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UsersRequest user) {
        return service.createNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        Map<String,String> response = service.verifyLoginUser(user);
        if (response.get("status").equals("success")) {
            return new ResponseEntity<Map<String,String>>(response, HttpStatus.OK);
        } else if (response.get("status").equals("internal-error")) {
            return new ResponseEntity<Map<String,String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String,String>>(response, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update-role")
    public ResponseEntity<?> updateRole(@RequestBody UsersRequest user) {
        return service.updateUserRole(user);
    }
}
