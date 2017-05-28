package app.dao;

import app.model.User;

/**
 * The interface for manipulating {@link app.model.User Users} in the database
 */
public interface UserDAOI {
    public User findById(int userId);
    public User findByName(String username);
    public boolean isExists(String username);

    public User updateUser(int userId, User user);
    public User createUser(User user);
    public User deleteUser(int userId);
}
