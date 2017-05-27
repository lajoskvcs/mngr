package app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The REST Controller what handles the / route
 */
@RestController
@RequestMapping(value = "/")
public class HomeController {
    /**
     * Returns a String
     * @return A {@link org.springframework.http.ResponseEntity ResponseEntity} filed with String
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("The server is up and running!");
    }
}
