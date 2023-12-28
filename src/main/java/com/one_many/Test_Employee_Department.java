package com.one_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test_Employee_Department {
    public static void main(String[] args) {
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder()
                .configure().build();
        Metadata metadata= new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory=metadata.getSessionFactoryBuilder().build();
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
            Department department=new Department();
            List<Employee> employees=new ArrayList<>();
            Employee zubair = new Employee();
            zubair.setEmployee_name("Zubair");
            Employee nihal = new Employee();
            nihal.setEmployee_name("Nihal");
            employees.add(nihal);
            employees.add(zubair);
            //department.setDepartment_id(1);
            department.setDepartment_name("Technical Team");
            department.setEmployee(employees);

            Department department2=new Department();
            Employee ashik=new Employee();
            ashik.setEmployee_name("ASHIK");
            department2.setDepartment_name("Accounts Department");
            department2.setEmployee(List.of(ashik));
            session.persist(department2);
            session.persist(department);
            transaction.commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
                System.out.println("Error :"+ e.getMessage());
            }
        }
        finally {
            session.close();sessionFactory.close();
        }
    }
}
