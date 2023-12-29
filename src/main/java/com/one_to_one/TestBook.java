package com.one_to_one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


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
            //author.setId(1);
            author.setName("M Zubair Basha");
            author.setBirthYear(1998);
            book.setTitle("Easy way of Learning Programming");
            book.setPublishedYear("2015");
            book.setAuthor(author);
            Book book2=new Book();
            Book book3=new Book();
            book2.setAuthor(author);
            book2.setTitle("Python Programming");
            book2.setPublishedYear("2016");
            book3.setTitle("Java Programming");
            book3.setPublishedYear("2020");
            book3.setAuthor(author);
            author.setBook(List.of(book,book2,book3));
           /* session.persist(book);*/

            Book findBooks=session.find(Book.class,1);
            //Author authorsValue=session.find(Author.class,1);
            //System.out.println(authorsValue);
            //System.out.println(findBooks);

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
