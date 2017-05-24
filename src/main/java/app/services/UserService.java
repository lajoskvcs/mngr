package app.services;


import app.dao.UserDAO;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService implements UserServiceI {
    @Autowired
    private UserDAO userDAO;

    @Override
    public User findById(int userId) {
        return userDAO.findById(userId);
    }

    @Override
    public User findByName(String username) {
        return userDAO.findByName(username);
    }

    @Override
    public boolean isExists(String username) {
        return userDAO.isExists(username);
    }

    @Override
    public User updateUser(int userId, User user) {
        return userDAO.updateUser(userId, user);
    }

    @Override
    public User deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }
}
