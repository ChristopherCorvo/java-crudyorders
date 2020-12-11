package com.lambdaschool.javaordersapp.services;


import com.lambdaschool.javaordersapp.models.Customer;

import java.util.List;

public interface CustomerServices
{
    // --------- Get request methods -------
    // find all customer orders
    List<Customer> findAllCustomerOrders();
    // find a customer with a substring
    List<Customer> findByNameLike(String substring);

    // Returns the customer with the given primary key
    Customer findCustomersById(long id);

    Customer save(Customer customer);
}
