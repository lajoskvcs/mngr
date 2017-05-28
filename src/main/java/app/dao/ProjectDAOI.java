package app.dao;


import app.model.Project;

import java.util.Collection;

/**
 * The interface for manipulating {@link app.model.Project Projects} in the database
 */
public interface ProjectDAOI {
    public Collection<Project> findAll(int userId);

    public Project findById(int projectId);

    public Project addProject(Project project);

    public Project updateProject(int projectId, Project project);

    public Project deleteProject(int projectId);
}
