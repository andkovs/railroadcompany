package com.railroad.model.dao;

import com.railroad.model.entity.Schedule;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDao {

    private final
    SessionFactory sessionFactory;

    @Autowired
    public ScheduleDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /**
     * Saves schedule in DB.
     *
     * @param schedule new schedule.
     */
    public void saveSchedule(Schedule schedule) {
        Session session = sessionFactory.openSession();
        session.save(schedule);
        session.flush();
    }

    public Long getTrainIdByScheduleId(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select trainId from Schedule where scheduleId = :id ");
        query.setParameter("id", id);
        return (Long)query.uniqueResult();
    }

    public void removeScheduleById(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("delete from Schedule where scheduleId= :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
