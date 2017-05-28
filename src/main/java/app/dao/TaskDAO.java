package app.dao;

import app.model.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Collection;

/**
 * The Repository class for manipulating {@link app.model.Task Tasks} in the database
 */
@Repository
public class TaskDAO implements TaskDAOI {
    @Autowired
    protected SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Collection<Task> findAllByUserId(int userId) {
        Session session = openSession();

        Query allTasksQuery = session.createQuery(
                "FROM Task t where t.project.id in (select id FROM Project where id in(select p.id From Project p join p.users u where u.id = :userId))"
        );
        allTasksQuery.setParameter("userId", userId);

        Collection<Task> allTasks = allTasksQuery.getResultList();

        return allTasks;
    }

    @Override
    public Collection<Task> findAll(int projectId) {
        Session session = openSession();

        Query allTasksQuery = session.createQuery("FROM Task where project_id = :projectId");
        allTasksQuery.setParameter("projectId", projectId);

        Collection<Task> allTasks = allTasksQuery.getResultList();

        return allTasks;
    }

    @Override
    public Task findById(int taskId) {
        Query resourceQuery = openSession().createQuery("FROM Task WHERE id = :taskId");
        resourceQuery.setParameter("taskId", taskId);

        Task task = (Task) resourceQuery.getResultList().get(0);

        if (task == null) {
            return null;
        }

        return task;
    }

    @Override
    public Task addTask(Task task) {
        openSession().save(task);
        return task;
    }

    @Override
    public Task updateTask(int taskId, Task task) {
        Task taskToUpdate = findById(taskId);

        taskToUpdate.setName(task.getName());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setStatus(task.getStatus());

        openSession().update(taskToUpdate);

        return taskToUpdate;
    }

    @Override
    public Task deleteTask(int taskId) {
        Task task = findById(taskId);
        if (task == null) {
            return null;
        }
        openSession().delete(task);
        return task;
    }
}
