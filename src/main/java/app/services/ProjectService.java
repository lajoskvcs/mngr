package app.services;

import app.dao.ProjectDAO;
import app.dao.ProjectDAOI;
import app.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class ProjectService implements ProjectServiceI {
    @Autowired
    private ProjectDAO projectDAO;

    @Override
    public Collection<Project> findAll(int userId) {
        return projectDAO.findAll(userId);
    }

    @Override
    public Project findById(int projectId) {
        return projectDAO.findById(projectId);
    }

    @Override
    public Project addProject(Project project) {
        return projectDAO.addProject(project);
    }

    @Override
    public Project updateProject(int projectId, Project project) {
        return projectDAO.updateProject(projectId, project);
    }

    @Override
    public Project deleteProject(int projectId) {
        return projectDAO.deleteProject(projectId);
    }

    @Override
    public int countAll(int userId) {
        Collection<Project> projects = findAll(userId);
        return (int) projects.stream().count();
    }
}
