package seu.edu.bd.southeast_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seu.edu.bd.southeast_portal.model.users.UsersRequest;
import seu.edu.bd.southeast_portal.service.AuthService;

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
    public ResponseEntity<?> login(@RequestBody UsersRequest user) {
        return service.verifyLoginUser(user);
    }

    @PutMapping("/update-role")
    public ResponseEntity<?> updateRole(@RequestBody UsersRequest user) {
        return service.updateUserRole(user);
    }
}
