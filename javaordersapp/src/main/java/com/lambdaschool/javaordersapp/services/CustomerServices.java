package com.lambdaschool.javaordersapp.services;


import com.lambdaschool.javaordersapp.models.Customer;

import java.util.List;

public interface CustomerServices
{
    // --------- Get request methods -------

    List<Customer> findAllCustomerOrders();

    List<Customer> findByNameLike(String substring);

    Customer findCustomersById(long id);

    Customer save(Customer customer);

    Customer update(Customer customer, long id);

    void delete(long id);

    void deleteAllCustomers();
}
