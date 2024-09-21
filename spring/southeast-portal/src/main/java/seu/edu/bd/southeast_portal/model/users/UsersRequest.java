package seu.edu.bd.southeast_portal.model.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersRequest {
    private String username;
    private String password;
    private String email;
    private String role;
}
