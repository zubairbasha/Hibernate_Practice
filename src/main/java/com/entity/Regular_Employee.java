package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "regular_employee1")
@PrimaryKeyJoinColumn(name = "id")
public class Regular_Employee extends Employee{

    private float salary;
    private int bonus;
    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
