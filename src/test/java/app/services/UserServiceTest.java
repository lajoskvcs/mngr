package app.services;

import app.dao.TaskDAO;
import app.dao.UserDAO;
import app.model.Project;
import app.model.Task;
import app.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    /**
     *
     */
    private User user1;

    /**
     *
     */
    private User user2;

    /**
     *
     */
    @Mock
    private UserDAO userDAO;

    /**
     *
     */
    @InjectMocks
    private UserService userService = new UserService();

    /**
     *
     */
    @Before
    public void setUp() {
        user1 = new User();
        user1.setId(1);
        user1.setUsername("user1");
        user1.setFirstName("User");
        user1.setLastName("One");
        user1.setEmail("asd@asd.hu");
        user1.setPassword("asd123");
        user1.setBornDate(LocalDate.of(1994,10,1));

        user2 = new User();
        user2.setId(2);
        user2.setUsername("user2");
        user2.setFirstName("User");
        user2.setLastName("One");
        user2.setEmail("asd@asd.hu");
        user2.setPassword("asd123");
        user2.setBornDate(LocalDate.of(1994,10,1));
    }

    /**
     *
     */
    @Test
    public void test__findById__should__return__the__correct__user() {
        when(userDAO.findById(1)).thenReturn(user1);
        when(userDAO.findById(2)).thenReturn(user2);
        User returnedUser = userService.findById(1);
        assertThat(returnedUser.getUsername(), is(equalTo(user1.getUsername())));
        returnedUser = userService.findById(2);
        assertThat(returnedUser.getUsername(), is(equalTo(user2.getUsername())));
    }

    /**
     *
     */
    @Test
    public void test__findByUsername__should__return__the__correct__user() {
        when(userDAO.findByName("user1")).thenReturn(user1);
        when(userDAO.findByName("user2")).thenReturn(user2);
        User returnedUser = userService.findByName("user1");
        assertThat(returnedUser.getId(), is(equalTo(user1.getId())));
        returnedUser = userService.findByName("user2");
        assertThat(returnedUser.getId(), is(equalTo(user2.getId())));
    }

    /**
     *
     */
    @Test
    public void test__isExists__should__return__true() {
        when(userDAO.isExists("user1")).thenReturn(true);
        assertThat(userService.isExists("user1"), is(true));
    }

    /**
     *
     */
    @Test
    public void test__isExists__should__return__false() {
        when(userDAO.isExists("user3")).thenReturn(false);
        assertThat(userService.isExists("user3"), is(false));
    }

    /**
     *
     */
    @Test
    public void test__addUser__should__return__with__the__created__user() {
        when(userDAO.createUser(user1)).thenReturn(user1);
        User returnedUser = userService.createUser(user1);
        assertThat(returnedUser.getId(), is(equalTo(user1.getId())));
        assertThat(returnedUser.getUsername(), is(equalTo(user1.getUsername())));
    }

    /**
     *
     */
    @Test
    public void test__update__user__should__return__with__the__updated__user() {
        when(userDAO.updateUser(1, user1)).thenReturn(user1);
        User returnedUser = userService.updateUser(1, user1);
        assertThat(returnedUser.getId(), is(equalTo(user1.getId())));
        assertThat(returnedUser.getUsername(), is(equalTo(user1.getUsername())));
    }

    /**
     *
     */
    @Test
    public void test__delete__user__should__return__with__the__deleted__user() {
        when(userDAO.deleteUser(1)).thenReturn(user1);
        User returnedUser = userService.deleteUser(1);
        assertThat(returnedUser.getId(), is(equalTo(user1.getId())));
        assertThat(returnedUser.getUsername(), is(equalTo(user1.getUsername())));
    }
}
