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

/**
 * This class implements the {@link org.springframework.security.provisioning.UserDetailsManager UserDetailsManager} from Spring Security.
 * It needed for the authorization process.
 */
public class CustomUserDetailsManager implements UserDetailsManager {
    @Autowired
    UserServiceI userService;

    /**
     * This method loads a user from the database by the username.
     * It is needed for authentication.
     * @param s The username of the user
     * @return A {@link org.springframework.security.core.userdetails.User User} with a granted authority
     * @throws UsernameNotFoundException When the user with the given username is not exists
     */
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

    /**
     * This method is not implemented.
     * @param userDetails The object what Spring Security uses for User handling
     */
    @Override
    public void createUser(UserDetails userDetails) {}

    /**
     * This method is not implemented.
     * @param userDetails The object what Spring Security uses for User handling
     */
    @Override
    public void updateUser(UserDetails userDetails) {}

    /**
     * This method is not implemented.
     * @param s The username of the user
     */
    @Override
    public void deleteUser(String s) {}

    /**
     * This method is not implemented.
     * @param s The old password
     * @param s1 The new password
     */
    @Override
    public void changePassword(String s, String s1) {}

    /**
     * This methods checks if the given username exists in the database.
     * @param s The username of the {@link app.model.User User}
     * @return A <code>Boolean</code> depends on the username is exists or not
     */
    @Override
    public boolean userExists(String s) {
        User user = userService.findByName(s);
        return user != null;
    }
}
