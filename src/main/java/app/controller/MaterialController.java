package app.controller;

import app.model.Material;
import app.model.User;
import app.services.MaterialServiceI;
import app.services.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * The REST Controller what handles requests for {@code Material} manipulation.
 */
@RestController
@RequestMapping(value = "/materials")
public class MaterialController {

    /**
     * This variable inject the {@code Logger} into the {@code Controller}.
     */
    Logger logger = LoggerFactory.getLogger(MaterialController.class);

    /**
     * This variable autowire the {@code MaterialService}.
     */
    @Autowired
    private MaterialServiceI materialService;

    /**
     * This variable autowire the {@code UserService}.
     */
    @Autowired
    private UserServiceI userService;

    /**
     * Deletes a {@code Material} with the given id.
     * @param id the {@code id} of the {@code Material}.
     * @return the deleted {@code Material}
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Material> deleteMaterial(@PathVariable("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userService.findByName(auth.getName());
        logger.info("[DELETE] /materials/"+id+" UserID: " + currentUser.getId());
        Material materialToDelete = materialService.deleteMaterial(id);
        return ResponseEntity.ok(materialToDelete);
    }

}
