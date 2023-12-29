package com.one_to_one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.TypedQuery;
import java.util.List;

public class TestBook {
    public static void main(String[] args) {
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata=new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory=metadata.getSessionFactoryBuilder().build();
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
            Book book=new Book();
            Author author=new Author();
            author.setName("M Zubair Basha");
            author.setBirthYear(1998);
            book.setTitle("Easy way of Learning Programming");
            book.setPublishedYear("2015");
            book.setAuthor(author);
            //session.persist(book);
            TypedQuery query=session.createQuery("FROM Book");
            List<Book> books=query.getResultList();
            books.forEach(
                    b->
                            System.out.println("Book: "+b.getTitle()+" Author:"+b.getAuthor().getName())


            );
            transaction.commit();
        }
        catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }
}
