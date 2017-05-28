package app.services;

import app.model.Material;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


/**
 * Interface for manipulating {@link app.model.Material Materials} in the database.
 */
public interface MaterialServiceI {
    /**
     * This method returns the {@link app.model.Material} with the given id.
     * @param materialId The id of the {@link app.model.Material}
     * @return The {@link app.model.Material} with the given id
     */
    public Material findById(int materialId);
    /**
     * This method returns a Collection of {@link app.model.Material Materials} with the given {@code taskId}.
     * @param taskId The id of the {@link app.model.Task}
     * @return The Collection of the {@link app.model.Material Materials}
     */
    public Collection<Material> findAllByTaskId(int taskId);
    /**
     * This method adds a new {@link app.model.Material} to the database.
     * @param material The {@link app.model.Material} to be added to the database
     * @return The added {@link app.model.Material}
     */
    public Material addMaterial(Material material);
    /**
     * This method update a {@link app.model.Material} with the given id.
     * @param materialId The id of the material to be updated
     * @param material The {@link app.model.Material} to be updated with
     * @return The updated {@link app.model.Material}
     */
    public Material updateMaterial(int materialId, Material material);
    /**
     * This method deletes a {@link app.model.Material} with the given id.
     * @param materialId The id of the {@link app.model.Material} to be deleted
     * @return The deleted {@link app.model.Material}
     */
    public Material deleteMaterial(int materialId);
}
