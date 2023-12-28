package com.entity;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestPosts {
    public static void main(String[] args) {
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
                .build();
        Metadata metadata=new MetadataSources(ssr)
                .getMetadataBuilder().build();
        SessionFactory factory=metadata.getSessionFactoryBuilder().build();
        Session session=factory.openSession();
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
            Post post=new Post();
            Set<String> tags=new HashSet<>();
            tags.add("#tntj");
            tags.add("#tntj");
            tags.add("#tntj");
            post.setTitle("TNTJ Social Work");
            post.setContent("This Post is about the Social work of TNTJ.");
            post.setTags(tags);
            session.persist(post);
           TypedQuery<Post> query=session.createQuery("from Post");
           List<Post> posts=query.getResultList();
           posts.forEach(value-> System.out.println(value.getId()+ " "+value.getTags()));
            transaction.commit();
            System.out.println("Saved Successfully");
        }catch(Exception e){
            if(transaction!=null) {
                transaction.rollback();
                System.out.println("Roll Back");
            }
            System.out.println(e.getMessage());
        }finally{
            session.close();
            factory.close();
        }
    }
}
