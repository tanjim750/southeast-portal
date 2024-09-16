package seu.edu.bd.southeast_portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seu.edu.bd.southeast_portal.model.Users;
import seu.edu.bd.southeast_portal.repository.UsersRepo;

@Service
public class UsersService {
    @Autowired
    private UsersRepo usersRepo;

    public Users getUserByUsername(String username) {
        return usersRepo.findByUsername(username);
    }

    public Users getUserById(int id) {
        return usersRepo.findById(id).get();
    }

    public Users addUser(Users user) {
        return usersRepo.save(user);
    }

}
