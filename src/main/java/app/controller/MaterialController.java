package app.controller;

import app.model.Material;
import app.services.MaterialServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The REST Controller what handles requests for {@code Material} manipulation.
 */
@RestController
@RequestMapping(value = "/materials")
public class MaterialController {

    /**
     * This variable autowire the {@code MaterialService}.
     */
    @Autowired
    private MaterialServiceI materialService;

    /**
     * Deletes a {@Code Material} with the given id.
     * @param id the {@code id} of the {@code Material}.
     * @return the deleted {@code Material}
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Material> deleteMaterial(@PathVariable("id") int id) {
        Material materialToDelete = materialService.deleteMaterial(id);
        return ResponseEntity.ok(materialToDelete);
    }

}
