package seu.edu.bd.southeast_portal.model.users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import seu.edu.bd.southeast_portal.authorization.Role;

import java.util.Collection;


public class UsersPrinciple implements UserDetails {
    Users user;
    Role userRole = Role.USER ;
    Role adminRole = Role.ADMIN ;
    Role stuffRole = Role.STUFF ;

    public UsersPrinciple(Users user) {
        this.user= user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(user.getRole().equals(adminRole.name())){
            return adminRole.getAuthority();
        }else if(user.getRole().equals(stuffRole.name())){
            return stuffRole.getAuthority();
        }else {
            return userRole.getAuthority();
        }
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
