package app.services;

import app.model.User;

/**
 * Interface for manipulating {@link app.model.User Users} in the database.
 */
public interface UserServiceI {
    /**
     * This method returns the {@link app.model.User User} with the given id.
     * @param userId The id of the {@link app.model.User User}
     * @return The {@link app.model.User User} with the given id
     */
    public User findById(int userId);

    /**
     * This method return the User with the given username.
     * @param username The username of the User
     * @return The {@link app.model.User User} with the given username
     */
    public User findByName(String username);

    /**
     * This method return a boolean depend on the user existence in the database.
     * @param username The username of the User
     * @return That user is exists or not
     */
    public boolean isExists(String username);

    /**
     * This method create a new User.
     * @param user The User to be created and saved into the database
     * @return The created {@link app.model.User User}
     */
    public User createUser(User user);

    /**
     * This method update a User b the given id.
     * @param userId The id of the User to be updated
     * @param user The User to be updated with
     * @return The updated {@link app.model.User User}
     */
    public User updateUser(int userId, User user);

    /**
     * This method deletes a User from the database.
     * @param userId The id of the user to be deleted
     * @return The delete {@link app.model.User User}
     */
    public User deleteUser(int userId);
}
