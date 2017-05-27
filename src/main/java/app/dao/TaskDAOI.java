package app.dao;


import app.model.Project;
import app.model.Task;

import java.util.Collection;

public interface TaskDAOI {

    public Collection<Task> findAllByUserId(int userId);

    public Collection<Task> findAll(int projectId);

    public Task findById(int taskId);

    public Task addTask(Task task);

    public Task updateTask(int taskId, Task task);

    public Task deleteTask(int taskId);
}
