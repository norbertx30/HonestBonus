package com.github.groupproject.entities;



import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(optional = false)
    private Client client;
    @OneToMany(mappedBy = "transaction")
    private Set<Payment> payments;
    @Column(name = "amount")
    private BigDecimal amountOfTransaction;

    public BigDecimal getAmountOfTransaction() {
        return amountOfTransaction;
    }

    public void setAmountOfTransaction(BigDecimal amountOfTransaction) {
        this.amountOfTransaction = amountOfTransaction;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
