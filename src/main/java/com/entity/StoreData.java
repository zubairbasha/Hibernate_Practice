package com.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;

// ... (imports remain unchanged)

public class StoreData {

    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata meta = new MetadataSources(ssr)
                .getMetadataBuilder()
                .build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = null;

        try {
            t = session.beginTransaction();

            Employee e1 = new Employee();
            e1.setName("Zubair Basha");

            Regular_Employee e2 = new Regular_Employee();
            e2.setName("Ashik");
            e2.setSalary(50000);
            e2.setBonus(5);

            Contract_Employee e3 = new Contract_Employee();
            e3.setName("Nihal Ahmed");
            e3.setContract_duration("5 Months");
            e3.setPay_per_hour(500);

       /*     session.persist(e1);
            session.persist(e2);
            session.persist(e3);*/
            session.get(1);
            t.commit();
            System.out.println("Saved Successfully");
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
                System.out.println("Rollback");
                e.printStackTrace();
            }
        } finally {
            session.close();
            factory.close();
        }
    }
}
