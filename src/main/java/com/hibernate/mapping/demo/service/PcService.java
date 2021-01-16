package com.hibernate.mapping.demo.service;


import com.hibernate.mapping.demo.model.Pc;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PcService {

    SessionFactory sessionFactory;

    PcService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Pc createPc(Pc pc) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        transaction.begin();
//        session.save(pc);
//        transaction.commit();
//        session.close();
        sessionFactory.getCurrentSession().persist(pc);
        return pc;
    }


    @Transactional
    public List<Pc> getAllPc() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Pc", Pc.class)
                .getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Pc getPcById(int id) {
        return (Pc) sessionFactory.getCurrentSession()
                .createQuery("FROM Pc d WHERE d.id = :id", Pc.class)
                //.createSQLQuery("select * FROM pc WHERE id = :id")
                .setParameter("id", id)
                .getSingleResult();
        //return sessionFactory.getCurrentSession().get(Pc.class, "1");
    }

    @Transactional
    public void deletePcById(int id) {
        Pc pc = getPcById(id);
        sessionFactory.getCurrentSession()
                .remove(pc);
        pc.setMotherboard("motherboard"); // detached object
        sessionFactory.getCurrentSession().delete(pc);
    }

    @Transactional
    public void updatePc(Pc pcNew, int id) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(pcNew);
    }

}