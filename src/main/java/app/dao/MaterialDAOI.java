package app.dao;


import app.model.Material;

import java.util.Collection;

/**
 * The interface for manipulating {@link app.model.Material Materials} in the database
 */
public interface MaterialDAOI {
    public Material findById(int materialId);
    public Collection<Material> findAllByTaskId(int taskId);
    public Material addMaterial(Material material);
    public Material updateMaterial(int materialId, Material material);
    public Material deleteMaterial(int materialId);
}
