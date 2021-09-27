package com.demo.hibernate.test;

import com.demo.hibernate.LoadSampleData;
import com.demo.hibernate.model.PersonEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaTest {

    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("persistence-unit");

    public static void main(String[] args) {
        try {
            persistPeople();
            findPersonsWithTasks();
        } finally {
            entityManagerFactory.close();
        }
    }

    public static void persistPeople() {
        LoadSampleData loadSampleData = new LoadSampleData();
        List<PersonEntity> persons = loadSampleData.loadData();

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(persons.get(0));
        em.persist(persons.get(1));
        em.getTransaction().commit();
        em.close();
        System.out.println("-- Persons persisted --");
    }

    private static void findPersonsWithTasks() {
        System.out.println("-- Find people with addresses --");
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PersonEntity> query = cb.createQuery(PersonEntity.class);
        Root<PersonEntity> persons = query.from(PersonEntity.class);
        persons.fetch("addresses", JoinType.LEFT);
        query.select(persons);
        TypedQuery<PersonEntity> typedQuery = em.createQuery(query);
        List<PersonEntity> resultList = typedQuery.getResultList();
        for (PersonEntity e : resultList) {
            System.out.println(e.getFirstName() + " - " + e.getAddresses());
        }

    }

}
