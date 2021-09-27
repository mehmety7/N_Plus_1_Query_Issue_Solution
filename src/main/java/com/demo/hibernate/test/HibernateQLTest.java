package com.demo.hibernate.test;

import com.demo.hibernate.LoadSampleData;
import com.demo.hibernate.model.PersonEntity;
import com.demo.hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class HibernateQLTest {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        LoadSampleData loadSampleData = new LoadSampleData();
        List<PersonEntity> persons = loadSampleData.loadData();

        session.persist(persons.get(0));
        session.persist(persons.get(1));

        session.getTransaction().commit();

        hqlSolution();

        HibernateUtil.shutdown();

    }

    public static void hqlSolution(){

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<PersonEntity> persons = session.createQuery("From PersonEntity p JOIN fetch p.addresses").setMaxResults(5).list();

            for (PersonEntity e : persons) {
                System.out.println(e.getFirstName() + " - " + e.getAddresses());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

}
