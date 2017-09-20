package app.model;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

public class ProjectTest {
    private Project project;
    private Project project2;
    private User user1;
    private Set<User> users = new HashSet<User>();
    private Collection<Task> tasks = new ArrayList<Task>();

    /**
     * Setup for the test
     */
    @Before
    public void setUp() {
        user1 = new User();
        user1.setId(1);
        user1.setUsername("user1");

        Task task1 = new Task();
        task1.setName("asd");
        task1.setMaterials(null);
        task1.setId(1);
        task1.setHourlySalary(2000);
        task1.setDescription("asdasdasd");
        task1.setStatus(0);
        task1.setPriority(0);
        task1.setTimes(null);
        task1.setProject(project);

        Task task2 = new Task();
        task2.setName("asd");
        task2.setMaterials(null);
        task2.setId(2);
        task2.setHourlySalary(2000);
        task2.setDescription("asdasdasd");
        task2.setStatus(1);
        task2.setPriority(0);
        task2.setTimes(null);
        task2.setProject(project);
        Task task4 = new Task();
        task2.setName("asd");
        task2.setMaterials(null);
        task2.setId(4);
        task2.setHourlySalary(2000);
        task2.setDescription("asdasdasd");
        task2.setStatus(1);
        task2.setPriority(0);
        task2.setTimes(null);
        task2.setProject(project);

        Task task3 = new Task();
        task3.setName("asd");
        task3.setMaterials(null);
        task3.setId(2);
        task3.setHourlySalary(2000);
        task3.setDescription("asdasdasd");
        task3.setStatus(2);
        task3.setPriority(0);
        task3.setTimes(null);
        task3.setProject(project);
        Task task5 = new Task();
        task5.setName("asd");
        task5.setMaterials(null);
        task5.setId(5);
        task5.setHourlySalary(2000);
        task5.setDescription("asdasdasd");
        task5.setStatus(2);
        task5.setPriority(0);
        task5.setTimes(null);
        task5.setProject(project);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        tasks.add(task5);

        project = new Project();
        project.setId(1);
        project.setDescription("Some description");
        project.setDueDate(LocalDate.of(2017,1,10));
        project.setName("Some name");
        project.setTasks(tasks);
        project.setUsers(users);

        project2 = new Project();
        project2.setId(1);
        project2.setDescription("Some description");
        project2.setDueDate(LocalDate.of(1994,10,10));
        project2.setName("Some name");
        project2.setTasks(null);
        project2.setUsers(users);
    }

    /**
     * Test for getProjectStatus
     */
    @Test
    public void test__getProjectStatus__should__return__the__correct__percentage() {
        assertThat(project.getProjectStatus(), is(equalTo(40.0)));
    }

    /**
     * Test for getProjectStatus
     */
    @Test
    public void test__getProjectStatus__should__return__the__zero() {
        assertThat(project2.getProjectStatus(), is(equalTo(0.0)));
    }
}
