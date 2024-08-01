package springmvc_hiber.service;

import springmvc_hiber.model.User;

import java.util.List;

public interface UserService {
    void saveNewUser(User user);

    void saveExistingUser(User user);

    List<User> listUsers();

    User getUser(long id);

    void deleteUser(long id);
}
