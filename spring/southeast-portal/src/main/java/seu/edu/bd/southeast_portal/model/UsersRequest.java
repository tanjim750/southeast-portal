package seu.edu.bd.southeast_portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class UsersRequest {
    private String username;
    private String password;
    private String email;
    private String role;
}
