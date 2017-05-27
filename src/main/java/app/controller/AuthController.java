package app.controller;

import app.manager.CustomUserDetailsManager;
import app.model.User;
import app.services.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

/**
 * The REST Controller what handles authentication requests
 */
@RestController
@RequestMapping("/users")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserServiceI userService;

    /**
     * Creates a new {@link app.model.User User} with the given parameters
     * @param user The {@link app.model.User User} from RequestBody
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the created {@link app.model.User User}
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Returns the currently authenticated {@link app.model.User User}
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the currently authenticated {@link app.model.User User}
     */
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public ResponseEntity<User> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByName(auth.getName());
        return ResponseEntity.ok(user);
    }


}
