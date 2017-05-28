package app.services;

import app.model.Project;

import java.util.Collection;

/**
 * The interface for manipulating {@link app.model.Project Projects} in the database.
 */
public interface ProjectServiceI {
    /**
     * This method returns the {@link app.model.Project Projects} for the given {@code userId}.
     * @param userId The id of the {@link app.model.User}
     * @return The {@code Collection} of the {@link app.model.Project Projects}
     */
    public Collection<Project> findAll(int userId);

    /**
     * This method returns the {@link app.model.Project} with the given id.
     * @param projectId The id of the {@link app.model.Project}
     * @return The {@link app.model.Project} with the given id
     */
    public Project findById(int projectId);

    /**
     * This method adds a new {@link app.model.Project} to the database.
     * @param project The {@link app.model.Project} to be added to the database
     * @return The added {@link app.model.Project}
     */
    public Project addProject(Project project);

    /**
     * This method updates a {@link app.model.Project} with the given id.
     * @param projectId The id of the {@link app.model.Project} to be updated
     * @param project The {@link app.model.Project} object what contains teh changes
     * @return The updated {@link app.model.Project}
     */
    public Project updateProject(int projectId, Project project);

    /**
     * This method deletes a {@link app.model.Project} with the given id.
     * @param projectId The id of the {@link app.model.Project} to be deleted
     * @return The deleted {@link app.model.Project}
     */
    public Project deleteProject(int projectId);

    /**
     * This method counts the {@link app.model.Project Projects} for the given {@code userId}.
     * @param userId The id of the {@link app.model.User}
     * @return The count of the {@link app.model.Project Projects}
     */
    public int countAll(int userId);
}
