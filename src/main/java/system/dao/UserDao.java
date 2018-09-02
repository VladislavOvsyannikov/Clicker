package system.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import system.model.User;
import system.service.HibernateSessionFactory;

import java.util.List;

@Repository
public class UserDao {

    public void saveUser(User user){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public void updateUser(User user){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public List<User> getAllUsers(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        List<User> list = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public User getUser(String name){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where name=:n");
        query.setParameter("n", name);
        if (query.list().size()!=0) {
            User user = (User) query.list().get(0);
            session.getTransaction().commit();
            session.close();
            return user;
        } else {
            session.getTransaction().commit();
            session.close();
            return null;
        }
    }
}
