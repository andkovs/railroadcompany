package com.railroad.model.dao;

import com.railroad.model.entity.Station;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StationDao {

    private final
    SessionFactory sessionFactory;

    @Autowired
    public StationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets all stations from DB.
     *
     * @return list of all stations.
     */
    public List getAllStations() {
        return sessionFactory.openSession().createCriteria(Station.class).list();
    }

    /**
     * Gets station by id from DB.
     *
     * @param id station id.
     * @return station.
     */
    public Station getStationById(Long id) {
        return (Station) sessionFactory.openSession().get(Station.class, id);
    }

    /**
     * Saves station in DB.
     *
     * @param station new station.
     * @return new station id.
     */
    public Long saveStation(Station station) {
        Session session = sessionFactory.openSession();
        Long id = (Long) session.save(station);
        session.flush();
        return id;
    }

    /**
     * Updates station in DB.
     *
     * @param station new station.
     */
    public void updateStation(Station station) {
        Session session = sessionFactory.openSession();
        session.update(station);
        session.flush();
    }

    /**
     * Removes station from DB by id.
     *
     * @param id removed station's id.
     */
    public void removeStation(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("delete from Station where stationId= :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
