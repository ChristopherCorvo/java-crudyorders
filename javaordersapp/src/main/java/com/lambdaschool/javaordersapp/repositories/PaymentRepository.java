package com.lambdaschool.javaordersapp.repositories;

import com.lambdaschool.javaordersapp.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long>
{

}
