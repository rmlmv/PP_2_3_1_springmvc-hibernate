package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User user5 = new User("User5", "Lastname5", "user5@mail.ru"
            , new Car("Model1", 1));
      User user6 = new User("User6", "Lastname6", "user6@mail.ru"
            , new Car("Model2", 2));
      User user7 = new User("User7", "Lastname7", "user7@mail.ru"
            , new Car("Model3", 3));
      User user8 = new User("User8", "Lastname8", "user8@mail.ru"
            , new Car("Model4", 4));

      userService.add(user5);
      userService.add(user6);
      userService.add(user7);
      userService.add(user8);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
         if (user.getCar() != null) {
            System.out.println(user.getCar());
         }
         System.out.println();
      }

      List<Optional<User>> usersWithCars = new ArrayList<>();
      usersWithCars.add(userService.getUserByCarModelAndCarSeries("Model1", 1));
      usersWithCars.add(userService.getUserByCarModelAndCarSeries("NotExistingModel", -1));
      System.out.println();

      for (Optional<User> OptionalUser : usersWithCars) {

         // В Java 1.8 нет ifPresentOrElse
         if (OptionalUser.isPresent()) {
            System.out.println(OptionalUser.get());
            System.out.println(OptionalUser.get().getCar());
            System.out.println();
         } else {
            System.out.println("Пользователь с таким автомобилем не найден.");
            System.out.println();
         }
      }

      context.close();
   }
}
