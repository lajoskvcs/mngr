package app.services;

import app.dao.TaskDAO;
import app.model.Project;
import app.model.Task;
import app.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {
    private Collection<Task> tasks = new ArrayList<Task>();
    private Task task1;
    private Project project1;
    private Set<User> users = new HashSet<User>();

    @Mock
    TaskDAO taskDAO;

    @InjectMocks
    private TaskService taskService = new TaskService();

    @Before
    public void setUp() {
        User user1 = new User();
        user1.setUsername("user1");

        task1 = new Task();
        task1.setName("asd");
        task1.setMaterials(null);
        task1.setId(1);
        task1.setHourlySalary(2000);
        task1.setDescription("asdasdasd");
        task1.setStatus(0);
        task1.setPriority(0);
        task1.setTimes(null);
        task1.setProject(project1);

        Task task2 = new Task();
        task2.setName("asd");
        task2.setMaterials(null);
        task2.setId(2);
        task2.setHourlySalary(2000);
        task2.setDescription("asdasdasd");
        task2.setStatus(1);
        task2.setPriority(0);
        task2.setTimes(null);
        task2.setProject(project1);
        Task task4 = new Task();
        task2.setName("asd");
        task2.setMaterials(null);
        task2.setId(4);
        task2.setHourlySalary(2000);
        task2.setDescription("asdasdasd");
        task2.setStatus(1);
        task2.setPriority(0);
        task2.setTimes(null);
        task2.setProject(project1);

        Task task3 = new Task();
        task3.setName("asd");
        task3.setMaterials(null);
        task3.setId(2);
        task3.setHourlySalary(2000);
        task3.setDescription("asdasdasd");
        task3.setStatus(2);
        task3.setPriority(0);
        task3.setTimes(null);
        task3.setProject(project1);
        Task task5 = new Task();
        task3.setName("asd");
        task3.setMaterials(null);
        task3.setId(5);
        task3.setHourlySalary(2000);
        task3.setDescription("asdasdasd");
        task3.setStatus(2);
        task3.setPriority(0);
        task3.setTimes(null);
        task3.setProject(project1);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        tasks.add(task5);

        project1 = new Project();
        project1.setId(1);
        project1.setDescription("Some description");
        project1.setDueDate(LocalDate.of(2017,10,10));
        project1.setName("Some name");
        project1.setTasks(tasks);
        project1.setUsers(users);

        Project project2 = new Project();
        project2.setId(2);
        project2.setDescription("asdasd");
        project2.setDueDate(LocalDate.of(2017,11,10));
        project2.setName("name2");
        project2.setTasks(null);
        project2.setUsers(users);


    }

    @Test
    public void test__findAll__should__return__with__all__the__tasks() {
        when(taskDAO.findAll(1)).thenReturn(tasks);
        Collection<Task> tasks = taskService.findAll(1);
        assertThat(tasks.size(), is(equalTo(5)));
    }
    @Test
    public void test__findAllByUserId__should__return__with__all__the__tasks() {
        when(taskDAO.findAllByUserId(1)).thenReturn(tasks);
        Collection<Task> tasks = taskService.findAllByUserId(1);
        assertThat(tasks.size(), is(equalTo(5)));
    }

    @Test
    public void test__findAllByUserId__should__return__null() {
        when(taskDAO.findAllByUserId(2)).thenReturn(null);
        Collection<Task> tasks = taskService.findAllByUserId(2);
        assertThat(tasks, is(equalTo(null)));
    }

    @Test
    public void test__findById__should__return__with__the__correct__project() {
        when(taskDAO.findById(1)).thenReturn(task1);
        Task returnedTasks = taskService.findById(1);
        assertThat(returnedTasks.getDescription(), is(equalTo(task1.getDescription())));
        assertThat(returnedTasks.getName(), is(equalTo(task1.getName())));
    }

    @Test
    public void test__addTask__should__return__with__the__created() {
        when(taskDAO.addTask(task1)).thenReturn(task1);
        Task returnedTask = taskService.addTask(task1);
        assertThat(returnedTask.getDescription(), is(equalTo(task1.getDescription())));
        assertThat(returnedTask.getName(), is(equalTo(task1.getName())));
    }

    @Test
    public void test__update__task__should__return__with__the__updated__task() {
        when(taskDAO.updateTask(1, task1)).thenReturn(task1);
        Task returnedTask = taskService.updateTask(1, task1);
        assertThat(returnedTask.getDescription(), is(equalTo(task1.getDescription())));
        assertThat(returnedTask.getName(), is(equalTo(task1.getName())));
    }


    @Test
    public void test__delete__task__should__return__with__the__deleted__task() {
        when(taskDAO.deleteTask(1)).thenReturn(task1);
        Task returnedTask = taskService.deleteTask(1);
        assertThat(returnedTask.getDescription(), is(equalTo(task1.getDescription())));
        assertThat(returnedTask.getName(), is(equalTo(task1.getName())));
    }

    @Test
    public void test__findInPlanTasks__should__return__correct__number() {
        when(taskDAO.findAllByUserId(1)).thenReturn(tasks);
        int inPlanTasks = taskService.findInPlanTasks(1);
        assertThat(inPlanTasks, is(equalTo(3)));
    }

    @Test
    public void test__findInProgressTasks__should__return__correct__number() {
        when(taskDAO.findAllByUserId(1)).thenReturn(tasks);
        int inProgressTasks = taskService.findInProgressTasks(1);
        assertThat(inProgressTasks, is(equalTo(1)));
    }

    @Test
    public void test__findDoneTasks__should__return__correct__number() {
        when(taskDAO.findAllByUserId(1)).thenReturn(tasks);
        int doneTasks = taskService.findDoneTasks(1);
        assertThat(doneTasks, is(equalTo(1)));
    }

}
