package app.dao;

import app.model.Project;
import app.model.User;

public interface UserDAOI {
    public User findById(int userId);
    public User findByName(String username);
    public boolean isExists(String username);

    public User updateUser(int userId, User user);
    public User createUser(User user);
    public User deleteUser(int userId);
}
