package dao;

import model.User;
import org.hibernate.Session;
import util.Util;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao{


    @Override
    public void createUsersTable() throws SQLException {
        Util.openTransaction();
        Session session = Util.getSession();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                "(id INT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(30)," +
                "lastname VARCHAR(30)," +
                "age TINYINT(100)," +
                "PRIMARY KEY (id))").executeUpdate();
       Util.closeTransaction();
    }
    @Override
    public void dropUsersTable() throws SQLException {
        Util.openTransaction();
        Session session = Util.getSession();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
       Util.closeTransaction();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Util.openTransaction();
        Session session = Util.getSession();
        session.save(new User(name, lastName, age));
       Util.closeTransaction();
    }
    @Override
    public void removeUserById(long id) throws SQLException {
        Util.openTransaction();
        Session session = Util.getSession();
        session.delete(session.load(User.class, id));
       Util.closeTransaction();
    }
    @Override
    public List<User> getAllUsers() throws  SQLException {
        Util.openTransaction();
        Session session = Util.getSession();
        List<User> users = null;

        users = session.createQuery("from User").list();
      Util.closeTransaction();
        return users;
    }
    @Override
    public void cleanUsersTable() throws SQLException {
        Util.openTransaction();
        Session session = Util.getSession();
        session.createSQLQuery("TRUNCATE TABLE users ");
        Util.closeTransaction();
    }
}
