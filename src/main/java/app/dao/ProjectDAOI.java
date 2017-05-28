package app.dao;


import app.model.Project;

import java.util.Collection;

/**
 * The interface for manipulating {@link app.model.Project Projects} in the database.
 */
public interface ProjectDAOI {
    public Collection<Project> findAll(int userId);

    /**
     * This method returns the {@link app.model.Project} with the given id
     * @param projectId The id of the {@link app.model.Project}
     * @return The {@link app.model.Project} with the given id
     */
    public Project findById(int projectId);

    /**
     * This method adds a new {@link app.model.Project} to the database
     * @param project The {@link app.model.Project} to be added to the database
     * @return The added {@link app.model.Project}
     */
    public Project addProject(Project project);

    public Project updateProject(int projectId, Project project);

    public Project deleteProject(int projectId);
}
