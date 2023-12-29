package com.composition;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class TestEmployee {
    public static void main(String[] args)  {
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder()
                .configure().build();
        Metadata metadata=new MetadataSources(ssr)
                .getMetadataBuilder().build();
        SessionFactory sessionFactory=metadata.getSessionFactoryBuilder()
                .build();
        Session sesssion=sessionFactory.openSession();
        Transaction transaction=null;
        try{
            transaction=sesssion.beginTransaction();
            Employee employee=new Employee();
            Address address=new Address();
            address.setCity("Chittoor");
            address.setCountry("India");
            address.setState("Andhra Pradesh");
            address.setPincode(517001);
            employee.setName("M Zubair Basha");
            employee.setAddress(address);
            /*sesssion.persist(employee);*/
            Query query=sesssion.createQuery("From Employee");
            query.setFirstResult(0);
            query.setMaxResults(3);
            List<Employee> list=query.getResultList();
            list.forEach(e-> System.out.println(e.getId()+" "+e.getName()+" "+e.getAddress().getCity()));
            Query query2=sesssion.createQuery("Update Employee set name=:n where id=:i");
          /*  query2.setParameter("n","Ashik");
            query2.setParameter("i",2);
            int status=query2.executeUpdate();*/
            //System.out.println(status);
            TypedQuery namedQuery=sesssion.getNamedQuery("findEmployeeByName");
            namedQuery.setParameter("name","M Zubair basha");
            List<Employee> employees=namedQuery.getResultList();
            employees.forEach(
                    e->System.out.println(e.getName()+ " "+e.getAddress().getCity())
            );
            transaction.commit();


        }catch (Exception e){
            if(transaction!=null){

                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }
}
