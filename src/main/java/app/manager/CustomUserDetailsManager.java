package app.manager;

import app.model.User;
import app.services.CustomUserDetailsService;
import app.services.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Collection;
import java.util.Collections;


public class CustomUserDetailsManager implements UserDetailsManager {
    @Autowired
    UserServiceI userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByName(s);
        Collection<GrantedAuthority> grantedAuthorities = Collections.singletonList((GrantedAuthority) () -> "ROLE_USER");
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities
        );
    }

    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {
        User user = userService.findByName(s);
        if(user != null) {
            userService.deleteUser(user.getId());
        }
    }

    @Override
    public void changePassword(String s, String s1) {

    }

    @Override
    public boolean userExists(String s) {
        User user = userService.findByName(s);
        return user != null;
    }
}
