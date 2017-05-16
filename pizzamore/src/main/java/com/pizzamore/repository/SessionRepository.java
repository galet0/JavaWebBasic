package com.pizzamore.repository;


import com.pizzamore.models.session.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class SessionRepository {

    public void createSession(Session session){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzaMore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(session);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public Session findSessionById(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzaMore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Session session = null;
        Query query = em.createQuery("SELECT s FROM Session AS s " +
                "WHERE s.id = :id");
        query.setParameter("id", id);
        if(query.getResultList().size() > 0){
            session = (Session) query.getSingleResult();
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
        return session;
    }

    public void deleteSessionById(long sid){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzaMore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Session AS s " +
                "WHERE s.id = :sid");
        query.setParameter("sid", sid);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
