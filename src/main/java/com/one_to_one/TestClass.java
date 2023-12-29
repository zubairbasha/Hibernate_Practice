package com.one_to_one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class TestClass {
    public static void main(String[] args) {
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata=new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory=metadata.getSessionFactoryBuilder().build();
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
            Employee employee=new Employee();
            employee.setName("M Zubair Basha");
            employee.setEmail("mohammedzubair006@gmail.com");
            Address address=new Address();
            address.setAddressLine1("6-371,Pillayar Koil Stree");
            address.setCity("Chittoor");
            address.setPincode(517001);
            address.setState("Andhra Pradesh");
            address.setCountry("India");
            employee.setAddress(address);
            address.setEmployee(employee);
            session.persist(employee);
            transaction.commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
                System.out.println("RollBacked");
                System.out.println(e.getMessage());
            }
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }
}
