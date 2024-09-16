package seu.edu.bd.southeast_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seu.edu.bd.southeast_portal.model.Users;
import seu.edu.bd.southeast_portal.service.UsersService;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    UsersService usersService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        System.out.println(user);
        try{
            Users newUser = usersService.addUser(user);
            return new ResponseEntity<Users>(newUser, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/create")
    public ResponseEntity<?> getUser(@RequestBody Users user) {
        System.out.println(user);
        try{
            Users newUser = usersService.addUser(user);
            return new ResponseEntity<Users>(newUser, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
