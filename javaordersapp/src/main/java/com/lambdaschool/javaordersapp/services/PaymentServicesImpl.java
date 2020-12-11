package com.lambdaschool.javaordersapp.services;

import com.lambdaschool.javaordersapp.models.Payment;
import com.lambdaschool.javaordersapp.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "paymentservices")
public class PaymentServicesImpl implements PaymentServices
{
    // we use autowire to wire in the agents repository
    @Autowired
    // we create a variable of type paymentrepository
    private PaymentRepository paymentRepository;

    @Transactional
    @Override
    public Payment save(Payment payment)
    {
        return paymentRepository.save(payment);
    }
}
