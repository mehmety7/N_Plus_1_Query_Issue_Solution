package com.demo.hibernate.test;

import com.demo.hibernate.LoadSampleData;
import com.demo.hibernate.model.AddressEntity;
import com.demo.hibernate.model.PersonEntity;
import com.demo.hibernate.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NPlusOneSelectsTest {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        LoadSampleData loadSampleData = new LoadSampleData();
        List<PersonEntity> persons = loadSampleData.loadData();

        session.persist(persons.get(0));
        session.persist(persons.get(1));

        session.getTransaction().commit();

        findAllPersonWithAddressesWithoutAnyHandle();

        HibernateUtil.shutdown();

    }

    public static void findAllPersonWithAddressesWithoutAnyHandle(){

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<PersonEntity> persons = session.createQuery("From PersonEntity").list();

            for (PersonEntity e : persons) {
                System.out.println(e.getFirstName() + " - " + e.getAddresses());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

}

