package com.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


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
            ArrayList<String> list1=new ArrayList<String>();
            list1.add("Java is a programming language");
            list1.add("Java is a platform");
            ArrayList<String> list2 = new ArrayList<String>();
            list2.add("Servlet is an Interface");
            list2.add("Servlet is an API");
            Question q1=new Question();
             q1.setQname("What is Java?");
             q1.setAnswers(list1);
             Question q2=new Question();
             q2.setQname("What is Servlet?");
             q2.setAnswers(list2);
             TypedQuery query= session.createQuery("from Question");
             System.out.println(query.getResultList());
             List<Question> list=query.getResultList();
             list.stream().forEach(question ->System.out.println("Question :"+question.getQname()+" Answer:"+question.getAnswers()));
         /*    session.persist(q1);
             session.persist(q2);*/
           t.commit();
           /* int i=10/0;
            Thread.sleep(6000);*/
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
