package springmvc_hiber.dao;

import springmvc_hiber.model.User;

import java.util.List;

public interface UserDao {
    void saveNewUser(User user);

    void saveExistingUser(User user);

    List<User> listUsers();

    User getUser(long id);

    void deleteUser(long id);
}
