package com.railroad.model.dao;


import com.railroad.model.entity.Direction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DirectionDao {

    private final
    SessionFactory sessionFactory;

    @Autowired
    public DirectionDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets all directions from DB.
     *
     * @return list of all directions.
     */
    public List<Direction> getAllDirections() {
        return sessionFactory.openSession().createCriteria(Direction.class).list();
    }

    /**
     * Gets list of directions from DB by
     * departure station id.
     *
     * @param id departure station id.
     * @return list of directions.
     */
    public List<Direction> getDirectionListByDepStationId(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Direction where depStationId = :id ");
        query.setParameter("id", id);
        return query.list();
    }

    /**
     * Saves direction in DB.
     *
     * @param direction new direction.
     */
    public void saveDirection(Direction direction) {
        Session session = sessionFactory.openSession();
        session.save(direction);
        session.flush();
    }

    /**
     * Removes direction from DB.
     *
     * @param direction removed direction.
     */
    public void removeDirection(Direction direction) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("delete from Direction where depStationId= :dId AND arrStationId= :aId");
        query.setParameter("dId", direction.getDepStationId());
        query.setParameter("aId", direction.getArrStationId());
        query.executeUpdate();
    }

    /**
     * Removes all directions from DB by station id.
     *
     * @param id station id.
     */
    public void removeDirectionsByStationId(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("delete from Direction where depStationId= :id OR arrStationId= :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    /**
     * Gets direction id by departure station id
     * and arrive station id from db.
     * @param depStationId departure station id.
     * @param arrStationId arrive station id.
     * @return direction id.
     */
    public Long getDirectionIdByDepStationIdAndArriveStationIdFromDb(Long depStationId, Long arrStationId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select directionId from Direction where depStationId= :dId AND arrStationId= :aId");
        query.setParameter("dId", depStationId);
        query.setParameter("aId", arrStationId);
        return (Long)query.uniqueResult();
    }

    /**
     * Gets list of directions from DB by
     * arrive station id.
     *
     * @param id arrive station id.
     * @return list of directions.
     */
    public List<Direction> getDirectionListByArrStationId(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Direction where arrStationId = :id ");
        query.setParameter("id", id);
        return query.list();
    }
}
