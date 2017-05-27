package app.services;

import app.model.User;

public interface UserServiceI {
    public User findById(int userId);
    public User findByName(String username);
    public boolean isExists(String username);
    public User createUser(User user);
    public User updateUser(int userId, User user);
    public User deleteUser(int userId);
}
