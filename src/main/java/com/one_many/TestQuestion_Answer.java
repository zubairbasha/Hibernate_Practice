package com.one_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class TestQuestion_Answer {
    public static void main(String[] args) {
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder()
                .configure().build();
        Metadata metadata= new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory=metadata.getSessionFactoryBuilder().build();
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
            Question question=new Question();
            question.setQname("what is java");
            List<Answer> answer=new ArrayList<>();
            Answer answer1=new Answer();
            answer1.setAnswerName("Java is a Object Oriented Language");
            answer1.setPostedBy("M Zubair Basha");
            answer.add(answer1);
            question.setAnswer(answer);
            /*session.persist(question);*/
            TypedQuery query=session.createQuery("FROM Question");
            List<Question> questionValue=query.getResultList();
            questionValue.forEach(q->System.out.println(q.getQname()+" :" +q.getAnswer().toArray()));

            transaction.commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
        }finally {
            session.close();
            sessionFactory.close();
        }

    }
}
