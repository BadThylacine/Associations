package com.hibernate.mapping.demo.service;


import com.hibernate.mapping.demo.model.Cpu;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CpuService {

    SessionFactory sessionFactory;

    CpuService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Cpu createCpu(Cpu cpu) {
        sessionFactory.getCurrentSession().persist(cpu);
        return cpu;
    }


    @Transactional
    public List<Cpu> getAllCpu() {
        return  sessionFactory.getCurrentSession()
                .createQuery("FROM Cpu", Cpu.class)
                .getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Cpu getCpuById(int id) {
        return  sessionFactory.getCurrentSession()
                .createQuery("FROM Cpu d WHERE d.id = :id", Cpu.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public void deleteCpuById(int id) {
        Cpu cpu = getCpuById(id);
        sessionFactory.getCurrentSession()
                .remove(cpu);
        cpu.setFrequency("4000 MHz"); // detached object
        sessionFactory.getCurrentSession().delete(cpu);
    }

    @Transactional
    public void updateCpuById(Cpu cpuNew, int id) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(cpuNew);
    }

//    @Transactional
//    public List<Cpu> listCpuOlderThan(String frequency) {
//        return sessionFactory.getCurrentSession()
//                .createNativeQuery("SELECT * FROM Cpu WHERE frequency > :frequency", Cpu.class)
//                .setParameter("frequency", frequency)
//                .getResultList();
//    }
//
//    @Transactional
//    public List<Cpu> listCpuOlderThanAverage() {
//        return sessionFactory.getCurrentSession()
//                .createQuery("SELECT p FROM Cpu p WHERE p.ram > (SELECT AVG(d.ram) FROM Cpu p)", Cpu.class)
//                .getResultList();
//    }
}