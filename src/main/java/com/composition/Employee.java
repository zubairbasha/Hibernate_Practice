package com.composition;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
@NamedQueries(
        {
                @NamedQuery(
                        name="findEmployeeByName",
                        query = "from Employee e where e.name=:name"
                )
        }
)
@Entity
@Table(name="emp_comp")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Embedded
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
