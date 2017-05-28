package app.dao;

import app.dao.ProjectDAOI;
import app.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * The Repository class for manipulating {@link app.model.Project Projects} in the database.
 */
@Repository
public class ProjectDAO implements ProjectDAOI {
    /**
     * This variable autowires the <code>SessionFactory</code> <code>Bean</code>.
     */
    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * This method returns teh current Session.
     * @return The current Session
     */
    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Project addProject(Project project) {
        openSession().save(project);
        return project;
    }

    @Override
    public Collection<Project> findAll(int userId) {
        Session session = openSession();

        Query allProjectsQuery = session.createQuery("FROM Project where id in(select p.id From Project p join p.users u where u.id = :userId)");
        allProjectsQuery.setParameter("userId", userId);

        List<Project> allProjects = allProjectsQuery.getResultList();

        return allProjects;
    }

    @Override
    public Project findById(int projectId) {
        Session session = openSession();

        Query resourceQuery = session.createQuery("FROM Project WHERE id = :projectId");
        resourceQuery.setParameter("projectId", projectId);

        Project project = (Project) resourceQuery.getResultList().get(0);

        if (project == null) {
            return null;
        }

        return project;
    }

    @Override
    public Project updateProject(int projectId, Project project) {
        Project projectToUpdate = findById(projectId);

        projectToUpdate.setName(project.getName());
        projectToUpdate.setDescription(project.getDescription());
        projectToUpdate.setDueDate(project.getDueDate());
        projectToUpdate.setUsers(project.getUsers());

        openSession().update(projectToUpdate);

        return projectToUpdate;
    }

    @Override
    public Project deleteProject(int projectId) {
        Project project = findById(projectId);
        if (project == null) {
            return null;
        }
        openSession().delete(project);
        return project;
    }
}
