package com.github.groupproject.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Bonus extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double shareOfTransaction;
    private Integer timeOutInDays;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "bonus")
    private Set<EBP> ebps;



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getShareOfTransaction() {
        return shareOfTransaction;
    }

    public void setShareOfTransaction(Double shareOfTransaction) {
        this.shareOfTransaction = shareOfTransaction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<EBP> getEbps() {
        return ebps;
    }

    public void setEbps(Set<EBP> ebps) {
        this.ebps = ebps;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimeOutInDays() {
        return timeOutInDays;
    }

    public void setTimeOutInDays(Integer timeOutInDays) {
        this.timeOutInDays = timeOutInDays;
    }
}
