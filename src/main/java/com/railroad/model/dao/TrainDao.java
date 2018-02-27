package com.railroad.model.dao;

import com.railroad.model.entity.Station;
import com.railroad.model.entity.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
