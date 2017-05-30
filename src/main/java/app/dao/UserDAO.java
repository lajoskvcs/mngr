package app.dao;

import app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * The Repository class for manipulating {@link app.model.User Users} in the database.
 */
@Repository
public class UserDAO implements UserDAOI {

    /**
     * This variable autowires the <code>SessionFactory</code> <code>Bean</code>.
     */
    @Autowired
    SessionFactory sessionFactory;

    /**
     * This method returns teh current Session.
     * @return The current Session
     */
    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public User findById(int userId) {
        List<User> userList = new ArrayList<User>();
        Query query = openSession().createQuery("FROM User u WHERE u.id = :userId");
        query.setParameter("userId", userId);

        userList = query.getResultList();

        if (userList.size() == 0) {
            return null;
        }

        return userList.get(0);
    }

    @Override
    public User findByName(String username) {
        List<User> userList = new ArrayList<User>();
        Query query = openSession().createQuery("FROM User u WHERE u.username = :username");
        query.setParameter("username", username);

        userList = query.getResultList();

        if (userList.size() == 0) {
            return null;
        }

        return userList.get(0);
    }

    @Override
    public User updateUser(int userId, User user) {
        openSession().update(user);
        return user;
    }

    @Override
    public User deleteUser(int userId) {
        return null;
    }

    @Override
    public User createUser(User user) {
        openSession().save(user);
        return user;
    }

    @Override
    public boolean isExists(String username) {
        User user = findByName(username);
        return (user != null);
    }
}
