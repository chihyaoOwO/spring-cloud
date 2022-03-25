package com.chihyao.springcloud.service;

import com.chihyao.springcloud.entities.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(String id);
}
