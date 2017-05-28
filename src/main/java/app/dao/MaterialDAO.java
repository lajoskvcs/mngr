package app.dao;

import app.model.Material;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;


/**
 * The Repository class for manipulating {@link app.model.Material Materials} in the database
 */
@Repository
public class MaterialDAO implements MaterialDAOI {
    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * This method is a shorthand for session getting
     * @return the current session
     */
    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Material findById(int materialId) {
        Query resourceQuery = openSession().createQuery("FROM Material WHERE id = :materialId");
        resourceQuery.setParameter("materialId", materialId);

        Material material = (Material) resourceQuery.getResultList().get(0);

        if (material == null) {
            return null;
        }

        return material;
    }

    @Override
    public Collection<Material> findAllByTaskId(int taskId) {
        Query resourceQuery = openSession().createQuery("FROM Material WHERE task_id = :taskId");
        resourceQuery.setParameter("taskId", taskId);
        List<Material> material = resourceQuery.getResultList();
        return material;
    }

    @Override
    public Material addMaterial(Material material) {
        openSession().save(material);
        return material;
    }

    @Override
    public Material updateMaterial(int materialId, Material material) {
        Material materialToUpdate = findById(materialId);
        materialToUpdate.setListPrice(material.getListPrice());
        materialToUpdate.setName(material.getName());
        materialToUpdate.setPercent(material.getPercent());
        materialToUpdate.setQuantity(material.getQuantity());
        materialToUpdate.setStoreName(material.getStoreName());
        materialToUpdate.setTask(material.getTask());
        openSession().update(materialToUpdate);
        return materialToUpdate;
    }

    @Override
    public Material deleteMaterial(int materialId) {
        Material materialToDelete = findById(materialId);
        openSession().delete(materialToDelete);
        return materialToDelete;
    }
}
