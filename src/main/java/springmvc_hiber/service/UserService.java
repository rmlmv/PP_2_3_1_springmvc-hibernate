package springmvc_hiber.service;

import springmvc_hiber.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    Optional<User> getUserByCarModelAndCarSeries(String model, int series);
}
