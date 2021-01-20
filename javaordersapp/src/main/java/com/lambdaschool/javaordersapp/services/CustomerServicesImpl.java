package com.lambdaschool.javaordersapp.services;


import com.lambdaschool.javaordersapp.models.Agent;
import com.lambdaschool.javaordersapp.models.Customer;
import com.lambdaschool.javaordersapp.models.Order;
import com.lambdaschool.javaordersapp.repositories.CustomerRepository;
import com.lambdaschool.javaordersapp.repositories.OrderRepository;
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

    @Autowired
    private OrderRepository orderRepository;

    // ----------- controller methods and services methods ---------
    // ----------- GET Requests -------------

    // GET: all customer orders
    @Override
    public List<Customer> findAllCustomerOrders()
    {
        List<Customer> listCustomerOrders = new ArrayList<>();
        custrepos.findAll()
            .iterator()
            .forEachRemaining(listCustomerOrders::add);
        return listCustomerOrders;
    }

    // This refers to the Get request defined in the controller and services
    @Override
    public Customer findCustomersById(long id)
    {
        return custrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found!"));
    }

    @Override
    public List<Customer> findByNameLike(String substring)
    {
        return custrepos.findByCustnameContainingIgnoringCase(substring);

    }


    // --------------- POST ------------
    // We will use the save method with our POST request
    @Transactional
    @Override
    // This is a Java tool -----> You use Override when ever you are porting in a method from an interface or class
    // it allows you to use the method name but change the business logic of the method
    public Customer save(Customer customer)
    {
        Customer newCustomer = new Customer();

        if (customer.getCustcode() != 0)
        {
            custrepos.findById(customer.getCustcode())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant " + customer.getCustcode() + " Not Found!"));

             newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        newCustomer.getOrders().clear();
//        for(Order o : customer.getOrders())
//        {
//            Order newOrders = orderRepository.findById(o.getOrdnum())
//                .orElseThrow(() -> new EntityNotFoundException("Order " + o.getOrdnum() + " Not found."));
//
//            newCustomer.getOrders().add(newOrders);
//        }
        for (Order o : customer.getOrders())
        {
            Order newOrder = new Order();
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setOrderdescription(o.getOrderdescription());
            newOrder.setOrdnum(o.getOrdnum());
            newOrder.setPayments(o.getPayments());
            newOrder.setCustomer(newCustomer);
        }
        // -------- End of Data Validation ----------
        return custrepos.save(customer);
    }
    @Transactional
    @Override
    public Customer update(
        Customer updateCustomer,
        long id)
    {
        Customer currentCustomer = custrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " not found."));

        if(updateCustomer.getCustname() != null)
        {
            currentCustomer.setCustname(updateCustomer.getCustname());
        }

        if(updateCustomer.getCustcity() != null)
        {
            currentCustomer.setCustcity(updateCustomer.getCustcity());
        }

        if(updateCustomer.getWorkingarea() !=null)
        {
            currentCustomer.setWorkingarea(updateCustomer.getWorkingarea());
        }

        if(updateCustomer.getCustcountry() !=null)
        {
            currentCustomer.setCustcountry(updateCustomer.getCustcountry());
        }

        if(updateCustomer.getGrade() != null)
        {
            currentCustomer.setGrade(updateCustomer.getGrade());
        }

        // If field is primitive value like double, then you need to use a
        // boolean flag to check! See Customer model
        // If it had a value set, then add to updateCustomer
        // ------------------------------------------------------ //
        if(updateCustomer.hasvalueforopeningamt)
        {
            currentCustomer.setOpeningamt(updateCustomer.getOpeningamt());
        }

        if(updateCustomer.hasvalueforreceiveamt)
        {
            currentCustomer.setReceiveamt(updateCustomer.getReceiveamt());
        }

        if(updateCustomer.hasvalueforpaymentamt)
        {
            currentCustomer.setPaymentamt(updateCustomer.getPaymentamt());
        }

        if(updateCustomer.hasvalueforoutstandingamt)
        {
            currentCustomer.setOutstandingamt(updateCustomer.getOutstandingamt());
        }
        // ------------------------------------------------------ //
        if(updateCustomer.getPhone() !=null)
        {
            currentCustomer.setPhone(updateCustomer.getPhone());
        }

        if(updateCustomer.getAgent() !=null)
        {
            currentCustomer.setAgent(updateCustomer.getAgent());
        }

        return custrepos.save(currentCustomer);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if(custrepos.findById(id).isPresent())
        {
            custrepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException("Customer " + id + " not found.");
        }
    }

    @Override
    public void deleteAllCustomers()
    {
        custrepos.deleteAll();
    }
}
