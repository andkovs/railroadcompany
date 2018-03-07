package com.railroad.model.dao;

import com.railroad.model.entity.Train;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainDao {

    private final
    SessionFactory sessionFactory;

    @Autowired
    public TrainDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets all trains from DB.
     *
     * @return list of all trains.
     */
    public List<Train> getAllTrains() {
        return sessionFactory.openSession().createCriteria(Train.class).list();
    }

    /**
     * Gets train by id from DB.
     *
     * @param id train id.
     * @return train.
     */
    public Train getTrainById(Long id) {
        return (Train) sessionFactory.openSession().get(Train.class, id);
    }

    /**
     * Saves train in DB.
     *
     * @param train new train.
     * @return new train id.
     */
    public Long saveTrain(Train train) {
        Session session = sessionFactory.openSession();
        Long id = (Long) session.save(train);
        session.flush();
        return id;
    }

    /**
     * Updates train in DB.
     *
     * @param train new train.
     */
    public void updateTrain(Train train) {
        Session session = sessionFactory.openSession();
        session.update(train);
        session.flush();
    }

    /**
     * Removes train from DB by id.
     *
     * @param id removed train's id.
     */
    public void removeTrain(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("delete from Train where trainId= :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


    /**
     * Gets train by title from DB.
     *
     * @param trainNumber train title.
     * @return train.
     */
    public Train getTrainByTrainNumber(String trainNumber) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Train where trainNumber= :trainNumber");
        query.setParameter("trainNumber", trainNumber);
        return (Train) query.uniqueResult();
    }
}
