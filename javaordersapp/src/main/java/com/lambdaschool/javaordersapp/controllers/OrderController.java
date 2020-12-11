package com.lambdaschool.javaordersapp.controllers;

import com.lambdaschool.javaordersapp.models.Order;
import com.lambdaschool.javaordersapp.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController
{

    @Autowired
    private OrderServices orderServices;

    // ---------- GET requests -----------
    // GET: http://localhost:2019/orders/order/{ordnum}
    @GetMapping(value = "/order/{ordnum}",
        produces = "application/json")
    public ResponseEntity<?> getOrdersById(
        @PathVariable long ordnum)
    {
        Order o = orderServices.findOrdersById(ordnum);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
    // ------------ POST -------------
    // POST http://localhost:2019/orders/order

    // ------------- PUT -------------
    // PUT http://localhost:2019/orders/order/63

    // -------------- DELETE ---------------
    // DELETE http://localhost:2019/orders/order/58

}

