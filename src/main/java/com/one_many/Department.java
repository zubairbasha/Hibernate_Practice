package com.one_many;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department_tb")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int department_id;
    private String department_name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="fr_department_id")
    @OrderColumn(name = "type")
    private List<Employee> employee;

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
