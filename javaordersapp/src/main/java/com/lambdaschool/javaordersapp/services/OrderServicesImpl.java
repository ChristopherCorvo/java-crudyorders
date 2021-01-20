package com.lambdaschool.javaordersapp.services;



import com.lambdaschool.javaordersapp.models.Order;
import com.lambdaschool.javaordersapp.models.Payment;
import com.lambdaschool.javaordersapp.repositories.CustomerRepository;
import com.lambdaschool.javaordersapp.repositories.OrderRepository;
import com.lambdaschool.javaordersapp.repositories.PaymentRepository;
import org.aspectj.weaver.ast.Or;
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

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // This refers to the Get request defined in the controller and services
    @Override
    public Order findOrdersById(long id)
    {
        return orderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order" + id + " Not Found!"));
    }

//    private double ordamount;
//    private double advanceamount;
//    private String orderdescription;
    // What is this about?
    @Transactional
    @Override
    public Order save(Order order)
    {
        Order newOrder = new Order();

        if(order.getOrdnum() != 0)
        {
            orderRepository.findById(order.getOrdnum())
                .orElseThrow(()-> new EntityNotFoundException("Order " + order.getOrdnum() + " not found."));

            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());
//        newOrder.setCustomer(order.getCustomer());
        newOrder.setCustomer(customerRepository.findById(order.getCustomer().getCustcode())
            .orElseThrow(()-> new EntityNotFoundException("Customer Not Found")));

        newOrder.getPayments().clear();
        for(Payment p : order.getPayments())
        {
            Payment newPay = paymentRepository.findById(p.getPaymentid())
                .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " not found."));

            newOrder.getPayments().add(newPay);
        }

        return orderRepository.save(newOrder);
    }

    @Transactional
    @Override
    public Order update(
        Order updateOrder,
        long id)
    {
        Order currentOrder = orderRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Order " + id + " not found."));

        // ------- Data Validation Start --------
        if(updateOrder.hasvalueforordamount)
        {
            currentOrder.setOrdamount(updateOrder.getOrdamount());
        }

        if(updateOrder.hasvalueforadvanceamount)
        {
            currentOrder.setAdvanceamount(updateOrder.getAdvanceamount());
        }

        if(updateOrder.getOrderdescription() != null)
        {
            currentOrder.setOrderdescription(updateOrder.getOrderdescription());
        }

        if(updateOrder.getCustomer() != null)
        {
            currentOrder.setCustomer(updateOrder.getCustomer());
        }

        // Validate a List

        if(updateOrder.getPayments().size() > 0)
        {
            currentOrder.getPayments().clear();
            for (Payment p : updateOrder.getPayments())
            {
                Payment newPay = paymentRepository.findById((p.getPaymentid()))
                    .orElseThrow(()-> new EntityNotFoundException("Payment " + p.getPaymentid() + " not found."));
                currentOrder.getPayments().add(newPay);
            }
        }

        return orderRepository.save(currentOrder);
    }

    @Transactional
    @Override
    public void delete(long ordnum)
    {
        if(orderRepository.findById(ordnum).isPresent())
        {
            orderRepository.deleteById(ordnum);
        }
        else
        {
            throw new EntityNotFoundException("Order " + ordnum + " not found.");
        }
    }
}
