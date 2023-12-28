package com.many_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.imageio.spi.ServiceRegistry;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder()
                .configure().build();
        Metadata metadata=new MetadataSources(ssr).getMetadataBuilder()
                .build();
        SessionFactory sessionFactory=metadata.getSessionFactoryBuilder()
                .build();
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
            Answer an1=new Answer();
            an1.setAnswername("Java is a programming language");
            an1.setPostedBy("Ravi Malik");

            Answer an2=new Answer();
            an2.setAnswername("Java is a platform");
            an2.setPostedBy("Sudhir Kumar");

            Question q1=new Question();
            q1.setQname("What is Java?");
            ArrayList<Answer> l1=new ArrayList<Answer>();
            l1.add(an1);
            l1.add(an2);
            q1.setAnswer(l1);


            Answer ans3=new Answer();
            ans3.setAnswername("Servlet is an Interface");
            ans3.setPostedBy("Jai Kumar");

            Answer ans4=new Answer();
            ans4.setAnswername("Servlet is an API");
            ans4.setPostedBy("Arun");

            Question q2=new Question();
            q2.setQname("What is Servlet?");
            ArrayList<Answer> l2=new ArrayList<Answer>();
            l2.add(ans3);
            l2.add(ans4);
            q2.setAnswer(l2);

          /*  session.persist(q1);
            session.persist(q2);*/
            TypedQuery query=session.createQuery("From Question");
            List<Question> q=query.getResultList();
            q.forEach(qu->System.out.println(qu.getQname()+" "+ Arrays.toString(qu.getAnswer().toArray())));
            TypedQuery query2=session.createQuery("From Answer");
            List<Answer> a=query2.getResultList();
            a.forEach(aa->
                    {
                        for(Question qss:aa.getQuestion()){
                            System.out.println(qss.getQname());
                        }
                        System.out.println(aa.getAnswername());
                    }
                    );
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();;
        }
    }
}
