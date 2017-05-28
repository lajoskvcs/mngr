package app.services;

import app.dao.MaterialDAO;
import app.model.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * The Service class for manipulating {@link app.model.Material Materials} in the database.
 */
@Service
@Transactional
public class MaterialService implements MaterialServiceI {
    /**
     * This variable autowire the <code>MaterialDAO</code>.
     */
    @Autowired
    MaterialDAO materialDAO;

    @Override
    public Material findById(int materialId) {
        return materialDAO.findById(materialId);
    }

    @Override
    public Collection<Material> findAllByTaskId(int taskId) {
        return materialDAO.findAllByTaskId(taskId);
    }

    @Override
    public Material addMaterial(Material material) {
        return materialDAO.addMaterial(material);
    }

    @Override
    public Material updateMaterial(int materialId, Material material) {
        return materialDAO.updateMaterial(materialId, material);
    }

    @Override
    public Material deleteMaterial(int materialId) {
        return materialDAO.deleteMaterial(materialId);
    }
}
