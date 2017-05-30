package app.controller;

import app.model.User;
import app.services.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller handles the authenticated {@link app.model.User} requests.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * This variable inject the {@code Logger} into the {@code Controller}.
     */
    Logger logger = LoggerFactory.getLogger(DashboardController.class);

    /**
     * This variable autowire the {@code UserService}.
     */
    @Autowired
    UserServiceI userService;

    /**
     * Updates a {@link app.model.User User} with the given parameters.
     * @param user The {@link app.model.User User} from RequestBody
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filled with the updated {@link app.model.User User}
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<User> update(@RequestBody User user) {
        User updated = userService.updateUser(user.getId(), user);
        return ResponseEntity.ok(updated);
    }

}
