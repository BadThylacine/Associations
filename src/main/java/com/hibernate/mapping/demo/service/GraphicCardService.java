package com.hibernate.mapping.demo.service;




import com.hibernate.mapping.demo.model.GraphicCard;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class GraphicCardService {

    SessionFactory sessionFactory;

    GraphicCardService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public GraphicCard createGraphicCard(GraphicCard graphicCard) {
        sessionFactory.getCurrentSession().persist(graphicCard);
        return graphicCard;
    }


    @Transactional
    public List<GraphicCard> getAllGraphicCard() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM GraphicCard", GraphicCard.class)
                .getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public GraphicCard getGraphicCardById(int id) {
        return (GraphicCard) sessionFactory.getCurrentSession()
                .createQuery("FROM GraphicCard d WHERE d.id = :id", GraphicCard.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public void deleteGraphicCardById(int id) {
        GraphicCard graphicCard = getGraphicCardById(id);
        sessionFactory.getCurrentSession()
                .remove(graphicCard);
        graphicCard.setName("Eddy"); // detached object
        sessionFactory.getCurrentSession().delete(graphicCard);
    }

    @Transactional
    public void updateGraphicCard(GraphicCard graphicCardNew, int id) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(graphicCardNew);
    }


}