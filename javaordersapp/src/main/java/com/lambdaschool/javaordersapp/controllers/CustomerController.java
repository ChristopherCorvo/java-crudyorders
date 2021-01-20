package com.lambdaschool.javaordersapp.controllers;

import com.lambdaschool.javaordersapp.models.Customer;
import com.lambdaschool.javaordersapp.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
        return new ResponseEntity<>(customerNameList,
            HttpStatus.OK);
    }

    // ----------- POST ----------
    // POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer",
        produces = "application/json",
        consumes = "application/json")
    public ResponseEntity<?> addNewCustomer(
        @Valid
        @RequestBody
            Customer newCustomer) // creating new Customer OBJ
    {
        // ids are not recognized by the POST method
        newCustomer.setCustcode(0); //Giving the new Customer OBJ an id#
        newCustomer = customerServices.save(newCustomer); // using the save method

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{custcode}")
            .buildAndExpand(newCustomer.getCustcode())
            .toUri();

        responseHeaders.setLocation(newCustomerURI);
        return new ResponseEntity<>(newCustomer,
            responseHeaders,
            HttpStatus.CREATED);

    }

    // ------------ PUT -------------
    // PUT http://localhost:2019/customers/customer/19
    @PutMapping(value = "/customer/{custcode}",
        produces = "application/json",
        consumes = "application/json")
    public ResponseEntity<?> updateFullCustomer(
        @Valid
        @RequestBody
            Customer updateCustomer,
        @PathVariable
            long custcode)
    {
        updateCustomer.setCustcode(custcode);
        updateCustomer = customerServices.save(updateCustomer);
        return new ResponseEntity<>(updateCustomer,
            HttpStatus.OK);
    }

    // ------------ Patch -----------
    // PATCH http://localhost:2019/customers/customer/19
    @PatchMapping(value = "/customer/{custcode",
        produces = "application/json",
        consumes = "application/json")
    public ResponseEntity<?> updateCustomer(
        @Valid
        @RequestBody
            Customer updateCustomer,
        @PathVariable
            long custcode)
    {
        updateCustomer = customerServices.update(updateCustomer, custcode);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    // ----------- DELETE -----------
    // DELETE http://localhost:2019/customers/customer/54
    @DeleteMapping("/customer/{custcode}")
    public ResponseEntity<?> deleteCustomerById(
        @PathVariable
            long custcode)
    {
        customerServices.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

