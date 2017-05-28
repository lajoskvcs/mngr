package app.dao;

import app.model.User;

/**
 * The interface for manipulating {@link app.model.User Users} in the database.
 */
public interface UserDAOI {
    /**
     * This method returns the {@link app.model.User} with the given id
     * @param userId The id of the {@link app.model.User}
     * @return The {@link app.model.User} with the given id
     */
    public User findById(int userId);
    public User findByName(String username);
    public boolean isExists(String username);

    public User updateUser(int userId, User user);
    /**
     * This method adds a new {@link app.model.User} to the database
     * @param user The {@link app.model.User} to be added to the database
     * @return The added {@link app.model.User}
     */
    public User createUser(User user);
    public User deleteUser(int userId);
}
