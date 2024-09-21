package seu.edu.bd.southeast_portal.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import seu.edu.bd.southeast_portal.model.users.Users;
import seu.edu.bd.southeast_portal.model.users.UsersRequest;
import seu.edu.bd.southeast_portal.repository.UsersRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthService {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    JwtService jwtService;

    @Value("${custom.bcrypt.strength}")
    private int bCryptStrength;
    private BCryptPasswordEncoder bCrypt;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void init(){
        bCrypt = new BCryptPasswordEncoder(bCryptStrength);
    }

    public boolean isValidPassword(String password, int minLength) {
        // The password must contain at least one letter, one digit, and meet the minimum length requirement
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{" + minLength + ",}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public boolean isValidEmail(String email) {
        // Regular expression to validate the email format
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public Users getUserByUsername(String username) {
        return usersRepo.findByUsername(username);
    }

    public Users getUserById(int id) {
        return usersRepo.findById(id).get();
    }

    public ResponseEntity<?> createNewUser(UsersRequest user) {
        Map<String,String> response = new HashMap<>();

        if(user.getUsername() == null || user.getEmail() == null || user.getPassword() == null){
            response.put("status","failed");
            response.put("message","Missing required parameters");
            response.put("parameters","username,password,email");

            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        if(user.getUsername().length() < 4 || user.getUsername().length() > 8){
            response.put("status","failed");
            response.put("message","username length should be between 4 and 8");

            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        if (!isValidEmail(user.getEmail())) {
            response.put("status","failed");
            response.put("message","Invalid email address");

            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        if(!isValidPassword(user.getPassword(), 8)){
            response.put("status","failed");
            response.put("message","Password length at least 8 and must contains character and digit");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        Users newUser = new Users();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bCrypt.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());

        try{
            Users savedUser = usersRepo.save(newUser);
            response.put("status","success");
            response.put("message","Account created successfully");
            response.put("id",String.valueOf(savedUser.getId()));
            response.put("username",savedUser.getUsername());
            response.put("email",savedUser.getEmail());
            response.put("role",savedUser.getRole());
            return new ResponseEntity<>(response,HttpStatus.CREATED);

        }catch (DataIntegrityViolationException e){

            response.put("status","failed");
            response.put("message","Username or email already exists");
            response.put("fix","Choose another username or email");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
            response.put("status","failed");
            response.put("message","Something went wrong");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Map<String,String>> verifyLoginUser(UsersRequest user) {
        Map<String,String> response = new HashMap<>();

        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

            if(authentication.isAuthenticated()) {
                response.put("status", "success");
                response.put("username", user.getUsername());
                response.put("role",authentication.getAuthorities().toString());
                response.put("token",jwtService.generateJwtToken(user.getUsername(),false));
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }catch (BadCredentialsException e) {
            response.put("status", "failed");
            response.put("error","Invalid username or password");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        response.put("status", "failed");
        response.put("error","Something went wrong");
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);


    }

    public ResponseEntity<?> updateUserRole(UsersRequest user) {
        Map<String,String> response = new HashMap<>();

        if(user.getRole() == null){

            response.put("status","failed");
            response.put("message","Invalid user role");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        Users findUser = usersRepo.findByUsername(user.getUsername());
        if(findUser == null){
            response.put("status","failed");
            response.put("message","User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        findUser.setRole(user.getRole());
        Users savedUser = usersRepo.save(findUser);
        response.put("status","success");
        response.put("message","User role updated successfully");
        response.put("id",String.valueOf(savedUser.getId()));
        response.put("username",savedUser.getUsername());
        response.put("email",savedUser.getEmail());
        response.put("role",savedUser.getRole());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
