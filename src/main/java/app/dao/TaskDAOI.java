package app.dao;


import app.model.Project;
import app.model.Task;

import java.util.Collection;

/**
 * The interface for manipulating {@link app.model.Task Tasks} in the database.
 */
public interface TaskDAOI {

    public Collection<Task> findAllByUserId(int userId);

    public Collection<Task> findAll(int projectId);

    /**
     * This method returns the {@link app.model.Task} with the given id
     * @param taskId The id of the {@link app.model.Task}
     * @return The {@link app.model.Task} with the given id
     */
    public Task findById(int taskId);

    /**
     * This method adds a new {@link app.model.Task} to the database
     * @param task The {@link app.model.Task} to be added to the database
     * @return The added {@link app.model.Task}
     */
    public Task addTask(Task task);

    public Task updateTask(int taskId, Task task);

    public Task deleteTask(int taskId);
}
