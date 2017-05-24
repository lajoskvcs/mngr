package app.services;


import app.dao.TaskDAO;
import app.model.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public interface TaskServiceI {
    public Collection<Task> findAll(int projectId);

    public Task findById(int taskId);

    public Task addTask(Task task);

    public Task updateTask(int taskId, Task task);

    public Task deleteTask(int taskId);
}
