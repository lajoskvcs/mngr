package app.services;


import app.model.Task;

import java.util.Collection;

/**
 * Interface for manipulating {@link app.model.Task Tasks} in the database.
 */
public interface TaskServiceI {

    /**
     * This method returns the {@link app.model.Task Tasks} for the given {@link app.model.User}.
     * @param UserId The id of the {@link app.model.User}
     * @return The {@link app.model.Task Tasks}
     */
    public Collection<Task> findAllByUserId(int UserId);

    /**
     * This method returns the {@link app.model.Task Tasks} for the given {@link app.model.Project}.
     * @param projectId The id of the {@link app.model.Project}
     * @return The {@link app.model.Task Tasks}
     */
    public Collection<Task> findAll(int projectId);

    /**
     * This method returns the {@link app.model.Task} with the given id.
     * @param taskId The id of the {@link app.model.Task}
     * @return The {@link app.model.Task} with the given id
     */
    public Task findById(int taskId);

    /**
     * This method adds a new {@link app.model.Task} to the database.
     * @param task The {@link app.model.Task} to be added to the database
     * @return The added {@link app.model.Task}
     */
    public Task addTask(Task task);

    /**
     * This method updated a {@link app.model.Task} with the given id.
     * @param taskId The id of the {@link app.model.Task} to be updated
     * @param task The {@link app.model.Task} object what contains the changes
     * @return The updated {@link app.model.Task}
     */
    public Task updateTask(int taskId, Task task);

    /**
     * This metod deletes a {@link app.model.Task} with the given id.
     * @param taskId The id of the {@link app.model.Task} to be deleted
     * @return The deleted {@link app.model.Task}
     */
    public Task deleteTask(int taskId);

    /**
     * This method returns the count of {@link app.model.Task Tasks} with {@code InPlan} {@code status}.
     * @param userId The is of the {@link app.model.User}
     * @return The count of {@link app.model.Task Tasks} with {@code InPlan} {@code status}
     */
    public int findInPlanTasks(int userId);

    /**
     * This method returns the count of {@link app.model.Task Tasks} with {@code InProgress} {@code status}.
     * @param userId The is of the {@link app.model.User}
     * @return The count of {@link app.model.Task Tasks} with {@code InProgress} {@code status}
     */
    public int findInProgressTasks(int userId);

    /**
     * This method returns the count of {@link app.model.Task Tasks} with {@code Done} {@code status}.
     * @param userId The is of the {@link app.model.User}
     * @return The count of {@link app.model.Task Tasks} with {@code Done} {@code status}
     */
    public int findDoneTasks(int userId);
}
