package app.services;

import app.model.Material;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;



public interface MaterialServiceI {
    public Material findById(int materialId);
    public Collection<Material> findAllByTaskId(int taskId);
    public Material addMaterial(Material material);
    public Material updateMaterial(int materialId, Material material);
    public Material deleteMaterial(int materialId);
}
