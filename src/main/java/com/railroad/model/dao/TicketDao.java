package com.railroad.model.dao;

import com.railroad.model.entity.Station;
import com.railroad.model.entity.Ticket;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDao {

    private final
    SessionFactory sessionFactory;

    @Autowired
    public TicketDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Integer getCountOfTicketsByTrainId(Long trainId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Ticket where trainId= :trainId");
        query.setParameter("trainId", trainId);
        return query.list().size();
    }

    /**
     * Saves ticket in DB.
     *
     * @param ticket new ticket.
     * @return new ticket id.
     */
    public void saveTicket(Ticket ticket) {
        Session session = sessionFactory.openSession();
        session.save(ticket);
        session.flush();
    }

    public Ticket getTicketByUserIdAndTrainId(Long userId, Long trainId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Ticket where trainId= :trainId AND userId= :userId");
        query.setParameter("userId", userId);
        query.setParameter("trainId", trainId);
        return (Ticket) query.uniqueResult();
    }
}
