package com.lambdaschool.javaordersapp.services;



import com.lambdaschool.javaordersapp.models.Order;

public interface OrderServices
{

    // --------- Get request methods -------
    // Returns the order with the given primary key
    Order findOrdersById(long id);
    Order save(Order order);
}

