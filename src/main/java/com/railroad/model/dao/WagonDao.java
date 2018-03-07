package com.railroad.model.dao;

import com.railroad.model.entity.Wagon;
import com.railroad.model.entity.WagonType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WagonDao {

    private final
    SessionFactory sessionFactory;

    @Autowired
    public WagonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<WagonType> getAllWagonTypes() {
        return sessionFactory.openSession().createCriteria(WagonType.class).list();
    }

    public Long getTrainIdByWagonId(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select trainId from Wagon where wagonId = :id ");
        query.setParameter("id", id);
        return (Long) query.uniqueResult();
    }

    public void removeWagonById(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("delete from Wagon where wagonId= :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void saveWagon(Wagon wagon) {
        Session session = sessionFactory.openSession();
        session.save(wagon);
        session.flush();
    }
}
