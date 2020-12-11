package com.lambdaschool.javaordersapp.controllers;

import com.lambdaschool.javaordersapp.models.Customer;
import com.lambdaschool.javaordersapp.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    @Autowired
    private CustomerServices customerServices;

    // ---------- GET requests -----------
    // GET: http://localhost:2019/customers/orders
    // List all orders
    @GetMapping(value = "/orders",
        produces = "application/json")
    public ResponseEntity<?> getAllCustomerOrders()
    {
        List<Customer> myCustomerOrders = customerServices.findAllCustomerOrders();
        return new ResponseEntity<>(myCustomerOrders,
            HttpStatus.OK);
    }

    // GET: http://localhost:2019/customers/customer/{custcode}
    // find particular customer via primary id
    @GetMapping(value = "/customer/{custcode}",
        produces = "application/json")
    public ResponseEntity<?> getCustomerById(
        @PathVariable
            long custcode)
    {
        Customer c = customerServices.findCustomersById(custcode);
        return new ResponseEntity<>(c,
            HttpStatus.OK);
    }

    // GET: http://localhost:2019/customers/namelike/{substring}
    @GetMapping(value = "/namelike/{substring}",
        produces = "application/json")
    public ResponseEntity<?> getCustomerByName(
        @PathVariable
            String substring)
    {
        List<Customer> customerNameList = customerServices.findByNameLike(substring);
        return new ResponseEntity<>(customerNameList, HttpStatus.OK);
    }

    // ----------- POST ----------
    // POST http://localhost:2019/customers/customer

    // ------------ PUT -------------
    // PUT http://localhost:2019/customers/customer/19

    // ------------ Patch -----------
    // PATCH http://localhost:2019/customers/customer/19

    // ----------- DELETE -----------
    // DELETE http://localhost:2019/customers/customer/54
}

