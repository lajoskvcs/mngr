package app.services;

import app.dao.NoteDAO;
import app.dao.UserDAO;
import app.model.Note;
import app.model.Project;

import app.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailsServiceTest {

    /**
     *
     */
    private User user1;

    /**
     *
     */
    @Mock
    private UserDAO userDAO;

    /**
     *
     */
    @InjectMocks
    private CustomUserDetailsService userDetailsService = new CustomUserDetailsService();

    /**
     *
     */
    @Before
    public void setUp() {
        this.user1 = new User();
        this.user1.setId(1);
        this.user1.setUsername("user1");
        this.user1.setPassword("asd123");
        this.user1.setBornDate(LocalDate.of(1994,10,1));
    }

    /**
     *
     */
    @Test
    public void test__loadUserByUsername__should__return__the__user__with__correct__parameters() {
        when(userDAO.findByName("user1")).thenReturn(this.user1);
        org.springframework.security.core.userdetails.UserDetails returnedUser = userDetailsService.loadUserByUsername("user1");
        assertThat(returnedUser.getUsername(), is(equalTo(this.user1.getUsername())));
        assertThat(returnedUser.getPassword(), is(equalTo(this.user1.getPassword())));
        assertThat(returnedUser.isEnabled(), is(true));
    }
}
