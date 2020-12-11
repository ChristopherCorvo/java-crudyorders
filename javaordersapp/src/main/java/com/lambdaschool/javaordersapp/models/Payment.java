package com.lambdaschool.javaordersapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payments")
public class Payment
{
    // Create primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // -------- Table Fields ----------
    private long paymentid; // primary key

    //    @Column(nullable = false,
    //            unique = true)
    private String type;

    // ------- Association --------
    @ManyToMany(mappedBy = "payments")
    @JsonIgnoreProperties("payments")
    private Set<Order> orders = new HashSet<>();

    // ----------- Constructors -----------
    public Payment()
    {
        // default constructor for JPA
    }

    // constructor with Parameters
    public Payment(String type)
    {
        this.type = type;
    }

    // ----------- Getters and Setters ---------

    public long getPaymentid()
    {
        return paymentid;
    }

    public void setPaymentid(long paymentid)
    {
        this.paymentid = paymentid;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Set<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(Set<Order> orders)
    {
        this.orders = orders;
    }


}

