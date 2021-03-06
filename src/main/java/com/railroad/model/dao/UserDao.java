package com.railroad.model.dao;

import com.railroad.model.entity.User;
import com.railroad.model.entity.UserRole;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    
    private final
    SessionFactory sessionFactory;

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets user by login from DB.
     *
     * @param login user login.
     * @return user.
     */
    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from User where login= :login");
        query.setParameter("login", login);
        return (User)query.uniqueResult();
    }

    /**
     * Gets user by id from DB.
     *
     * @param id user id.
     * @return user.
     */
    public User getUserById(Long id) {
        return (User) sessionFactory.openSession().get(User.class, id);
    }

    /**
     * Saves user in DB.
     *
     * @param user new user.
     */
    public void saveUser(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.flush();
    }

    /**
     * Updates user in DB.
     *
     * @param user new user.
     */
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("update User set firstName=:firstName, lastName=:lastName, middleName=:middleName, phone=:phone, email=:email where userId=:userId");
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("middleName", user.getMiddleName());
        query.setParameter("phone", user.getPhone());
        query.setParameter("email", user.getEmail());
        query.setParameter("userId", user.getUserId());
        query.executeUpdate();
    }

    /**
     * Saves user_role in DB.
     *
     * @param userRole new userRole.
     */
    public void saveUserRole(UserRole userRole) {
        Session session = sessionFactory.openSession();
        session.save(userRole);
        session.flush();
    }
}
