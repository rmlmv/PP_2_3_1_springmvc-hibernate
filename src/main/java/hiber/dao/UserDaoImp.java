package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

   private SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
      if (user.getCar() != null) {
         sessionFactory.getCurrentSession().save(user.getCar());
      }
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public Optional<User> getUserByCarModelAndCarSeries(String model, int series) {
      Optional<User> result;
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("select u from User u join u.car c " +
                      "where c.model = :model and c.series = :series", User.class);

      query.setParameter("model", model);
      query.setParameter("series", series);
      try {
         result = Optional.of(query.getSingleResult());
      } catch (NoResultException e) {
         result = Optional.empty();
      }

      return result;
   }

}
