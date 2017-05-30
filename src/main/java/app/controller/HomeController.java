package app.controller;

import app.model.User;
import app.services.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The REST Controller what handles the / route.
 */
@RestController
@RequestMapping(value = "/")
public class HomeController {

    /**
     * This variable inject the {@code Logger} into the {@code Controller}.
     */
    Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * This variable autowire the {@code UserService}.
     */
    @Autowired
    UserServiceI userService;

    /**
     * Returns a String.
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filed with String
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        logger.info("[GET] / ");
        return ResponseEntity.ok("The server is up and running!");
    }
}
