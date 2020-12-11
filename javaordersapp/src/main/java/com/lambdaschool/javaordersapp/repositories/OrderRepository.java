package com.lambdaschool.javaordersapp.repositories;

import com.lambdaschool.javaordersapp.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>
{

}
