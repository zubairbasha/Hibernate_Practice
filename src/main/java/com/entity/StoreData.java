package com.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;

public class StoreData {

    public static void main(String[] args) {
        /*Once the application is started the sessionfactory will be created file  from hibernate.cfg.xml;
        * */
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
                .build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        System.out.println(meta.getDatabase().toString());
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t=null;
       try{
            t = session.beginTransaction();
            Employee e1 = new Employee();
            e1.setId(2);
            e1.setFirstName("Nihal");
            e1.setLastName("Basha");
            session.update(e1);
            Thread.sleep(10000);
            //int i=10/0;
            Employee empl = session.get(Employee.class, 1);
            System.out.println(empl.getFirstName());
            t.commit();
            System.out.println("Saved Successfully");

        }catch(Exception e){
            if(t!=null){
                t.rollback();
               System.out.println("Roll backed");
            }
        }finally{
           factory.close();
           session.close();
        }
    }
}
