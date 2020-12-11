package com.lambdaschool.javaordersapp.services;



import com.lambdaschool.javaordersapp.models.Customer;
import com.lambdaschool.javaordersapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerservices")
public class CustomerServicesImpl implements CustomerServices
{
    // We use autowire to wire in customerservices
    @Autowired
    // we create a variable of type customerrepository
    private CustomerRepository custrepos; // why the lowercase?

    // ----------- controller methods and services methods ---------
    // GET: all customer orders
    @Override
    public List<Customer> findAllCustomerOrders()
    {
        List<Customer> listCustomerOrders = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(listCustomerOrders::add);
        return listCustomerOrders;
    }

    // This refers to the Get request defined in the controller and services
    @Override
    public Customer findCustomersById(long id)
    {
        return custrepos.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Customer " + id + " Not Found!"));
    }

    @Override
    public List<Customer> findByNameLike(String substring)
    {
        return custrepos.findByCustnameContainingIgnoringCase(substring);

    }

    @Transactional
    @Override // This is a Java tool -----> You use Override when ever you are porting in a method from an interface or class
    // it allows you to use the method name but change the business logic of the method
    public Customer save(Customer customer)
    {
        return custrepos.save(customer);
    }
}
