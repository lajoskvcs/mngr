package app.dao;

import app.model.User;

/**
 * The interface for manipulating {@link app.model.User Users} in the database.
 */
public interface UserDAOI {
    /**
     * This method returns the {@link app.model.User} with the given id.
     * @param userId The id of the {@link app.model.User}
     * @return The {@link app.model.User} with the given id
     */
    public User findById(int userId);

    /**
     * This method returns the {@link app.model.User} with the given username.
     * @param username The username of the {@link app.model.User}
     * @return The {@link app.model.User} with the given username
     */
    public User findByName(String username);

    /**
     * This method returns a {@code Boolean} depend on the {@link app.model.User Users's} existence.
     * @param username The username of the {@link app.model.User}
     * @return {@code Boolean} depends on the {@link app.model.User User's} existence
     */
    public boolean isExists(String username);

    /**
     * This method updated a {@link app.model.User} with the given id.
     * @param userId The id of the {@link app.model.User} to be updated
     * @param user The {@link app.model.User} object what contains the changes
     * @return The updated {@link app.model.User}
     */
    public User updateUser(int userId, User user);
    /**
     * This method adds a new {@link app.model.User} to the database.
     * @param user The {@link app.model.User} to be added to the database
     * @return The added {@link app.model.User}
     */
    public User createUser(User user);

    /**
     * Thid method deletes a {@link app.model.User} with the given id.
     * @param userId The id of the {@link app.model.User} to be deleted
     * @return The deleted {@link app.model.User}
     */
    public User deleteUser(int userId);
}
