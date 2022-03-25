package com.chihyao.springcloud.controller;

import com.chihyao.springcloud.entities.CommonResult;
import com.chihyao.springcloud.entities.Payment;
import com.chihyao.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult<Integer> createPayment(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result>0) {
            return new CommonResult<>(200, "success, serverPort : "+serverPort, result);
        } else {
            return new CommonResult<>(444, "failed");
        }
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") String id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult<>(200, "success, serverPort : "+serverPort, payment);
        } else {
            return new CommonResult<>(444, "failed");
        }
    }
}
