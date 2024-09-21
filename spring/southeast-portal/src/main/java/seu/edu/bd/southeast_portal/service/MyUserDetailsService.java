package seu.edu.bd.southeast_portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import seu.edu.bd.southeast_portal.model.users.Users;
import seu.edu.bd.southeast_portal.model.users.UsersPrinciple;
import seu.edu.bd.southeast_portal.repository.UsersRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Provided username '" + username + "' does not exist.");
        }
        return new UsersPrinciple(user);
    }
}
