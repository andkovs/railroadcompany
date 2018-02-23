package com.railroad.model.dao;

import com.railroad.model.entity.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDao {

    private final
    SessionFactory sessionFactory;

    @Autowired
    public TestDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List getAllTests(){
        Session session = sessionFactory.openSession();
        return session.createCriteria(Test.class).list();
    }
}
