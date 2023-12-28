package com.entity;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name = "employee102")
@Inheritance(strategy = InheritanceType.JOINED)

public class Employee {


   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
    private int id;
   @Column(name = "name")
    private String name;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
