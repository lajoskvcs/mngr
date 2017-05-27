package app.services;

import app.dao.ProjectDAO;
import app.model.Project;
import app.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {
    private Collection<Project> projects = new ArrayList<Project>();
    private Project project;
    private Set<User> users = new HashSet<User>();
    private int userId = 1;

    @Mock
    ProjectDAO projectDAO;

    @InjectMocks
    private ProjectService projectService = new ProjectService();

    @Before
    public void setUp() {
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");

        this.users.add(user1);
        this.users.add(user2);

        project = new Project();
        project.setId(1);
        project.setDescription("Some description");
        project.setDueDate(LocalDate.of(2017,10,10));
        project.setName("Some name");
        project.setTasks(null);
        project.setUsers(users);

        Project project2 = new Project();
        project.setId(2);
        project.setDescription("asdasd");
        project.setDueDate(LocalDate.of(2017,11,10));
        project.setName("name2");
        project.setTasks(null);
        project.setUsers(users);

        projects.add(project);
        projects.add(project2);
    }

    @Test
    public void test__findAll__should__return__with__all__the__projects() {
        when(projectDAO.findAll(userId)).thenReturn(projects);
        Collection<Project> projects = projectService.findAll(userId);
        assertThat(projects.size(), is(equalTo(2)));
    }
    @Test
    public void test__findById__should__return__with__the__correct__project() {
        when(projectDAO.findById(1)).thenReturn(project);
        Project returnedProject = projectService.findById(1);
        assertThat(returnedProject.getDescription(), is(equalTo(project.getDescription())));
        assertThat(returnedProject.getName(), is(equalTo(project.getName())));
        assertThat(returnedProject.getDueDate(), is(equalTo(project.getDueDate())));
        assertThat(returnedProject.getUsers(), is(equalTo(project.getUsers())));
    }

    @Test
    public void test__addProject__should__return__with__the__created() {
        when(projectDAO.addProject(project)).thenReturn(project);
        Project returnedProject = projectService.addProject(project);
        assertThat(returnedProject.getDescription(), is(equalTo(project.getDescription())));
        assertThat(returnedProject.getName(), is(equalTo(project.getName())));
        assertThat(returnedProject.getDueDate(), is(equalTo(project.getDueDate())));
        assertThat(returnedProject.getUsers(), is(equalTo(project.getUsers())));
    }

    @Test
    public void test__update__project__should__return__with__the__updated__project() {
        when(projectDAO.updateProject(1, project)).thenReturn(project);
        Project returnedProject = projectService.updateProject(1, project);
        assertThat(returnedProject.getDescription(), is(equalTo(project.getDescription())));
        assertThat(returnedProject.getName(), is(equalTo(project.getName())));
        assertThat(returnedProject.getDueDate(), is(equalTo(project.getDueDate())));
        assertThat(returnedProject.getUsers(), is(equalTo(project.getUsers())));
    }


    @Test
    public void test__delete__project__should__return__with__the__deleted__project() {
        when(projectDAO.deleteProject(1)).thenReturn(project);
        Project returnedProject = projectService.deleteProject(1);
        assertThat(returnedProject.getDescription(), is(equalTo(project.getDescription())));
        assertThat(returnedProject.getName(), is(equalTo(project.getName())));
        assertThat(returnedProject.getDueDate(), is(equalTo(project.getDueDate())));
        assertThat(returnedProject.getUsers(), is(equalTo(project.getUsers())));
    }

    @Test
    public void test__countAll__should__return__with__the__correct__amount__of__projects() {
        when(projectDAO.findAll(userId)).thenReturn(projects);
        assertThat(projectService.countAll(userId), is(equalTo(projects.size())));
    }

}
