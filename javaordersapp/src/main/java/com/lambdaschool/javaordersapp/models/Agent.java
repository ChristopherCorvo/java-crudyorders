package com.lambdaschool.javaordersapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agents") // If you were to use a "GET ALL" query you would get back all 'Agent' objects and we
                        // would name it plural because it will be multiple 'Agent' objects.
                        // We are making a series of 'Agent' objects that will be stored in the 'agents' table
@JsonIgnoreProperties
public class Agent
{
    // Create Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // standard
    // ---------- Table Fields ---------
    private long agentcode; // primary key needs to not be null

    private String agentname;
    private String workingarea;
    private double commission;
    private String phone;
    private String country;

    // Association table fields
    @OneToMany(mappedBy = "agent", //'agent' is reffering to the variable created in the customer table
                        cascade = CascadeType.ALL,
                        orphanRemoval = true)
    @JsonIgnoreProperties(value = "agents", allowSetters = true) // what does this mean?
    private List<Customer> customer = new ArrayList<>();

    // ------------ Constructors ----------
    public Agent()
    {
        // default constructor for JPA
    }

    //Constructor with parameters
    // We dont include the primary key in the constructor
    public Agent(
        String agentname,
        String workingarea,
        double commission,
        String phone,
        String country
        )
    {
        this.agentname = agentname;
        this.workingarea = workingarea;
        this.commission = commission;
        this.phone = phone;
        this.country = country;
    }

    // ------------ Getters and Setters ---------
    // Remember to make a getter and setter for the primary key
    public long getAgentcode()
    {
        return agentcode;
    }

    public void setAgentcode(long agentcode)
    {
        this.agentcode = agentcode;
    }

    public String getAgentname()
    {
        return agentname;
    }

    public void setAgentname(String agentname)
    {
        this.agentname = agentname;
    }

    public String getWorkingarea()
    {
        return workingarea;
    }

    public void setWorkingarea(String workingarea)
    {
        this.workingarea = workingarea;
    }

    public double getCommission()
    {
        return commission;
    }

    public void setCommission(double commission)
    {
        this.commission = commission;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public List<Customer> getCustomer()
    {
        return customer;
    }

    public void setCustomer(List<Customer> customer)
    {
        this.customer = customer;
    }
}
