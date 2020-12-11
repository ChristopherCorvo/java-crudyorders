package com.lambdaschool.javaordersapp.services;



import com.lambdaschool.javaordersapp.models.Order;
import com.lambdaschool.javaordersapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderservices") //creating a connection to OrderServices
public class OrderServicesImpl implements OrderServices
{
    // we use @autowire to wire in the services
    @Autowired
    // we create a variable of type OrderRepository
    private OrderRepository orderRepository; // this is a reference point to orderRepository

    // This refers to the Get request defined in the controller and services
    @Override
    public Order findOrdersById(long id)
    {
        return orderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order" + id + " Not Found!"));
    }

    // What is this about?
    @Transactional
    @Override
    public Order save(Order order)
    {
        return orderRepository.save(order);
    }
}
