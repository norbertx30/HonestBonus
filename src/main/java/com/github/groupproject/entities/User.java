package com.github.groupproject.entities;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String companyName;
    @Email
    private String email;
    @OneToMany(mappedBy = "user")
    private Set<Client> clients;
    @OneToMany(mappedBy = "user")
    private Set<Employee> employees;
    @OneToMany(mappedBy = "user")
    private Set<Bonus> bonuses;
    private String password;


    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Bonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(Set<Bonus> bonuses) {
        this.bonuses = bonuses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
