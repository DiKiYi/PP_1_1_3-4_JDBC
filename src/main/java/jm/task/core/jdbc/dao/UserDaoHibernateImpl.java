package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.PersistenceException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getConfiguration().buildSessionFactory();
    }


    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("create table users (id int auto_increment,name " +
                    "varchar(40) null,lastName varchar(40) null,age int null, " +
                    "constraint users_pk primary key (id))").executeUpdate();
            session.getTransaction().commit();
        }catch (PersistenceException e){}



    }

    @Override
    public void dropUsersTable() {
        try {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("drop table users").executeUpdate();
        session.getTransaction().commit();
    }catch (PersistenceException e){}

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        System.out.println("User с именем – " + name +" добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        User del = session.get(User.class, id);
        session.delete(del);
        session.getTransaction().commit();

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        userList = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();

    }
}
