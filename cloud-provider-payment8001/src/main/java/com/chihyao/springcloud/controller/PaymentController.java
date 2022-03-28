package com.chihyao.springcloud.controller;

import com.chihyao.springcloud.entities.CommonResult;
import com.chihyao.springcloud.entities.Payment;
import com.chihyao.springcloud.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Log4j2
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/payment/discovery")
    public void discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*********services = " + service);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") String id) {
        Payment payment = new Payment();
        payment.setPayment_id(id);
        if (payment != null) {
            return new CommonResult<>(200, "success, serverPort : "+serverPort, payment);
        } else {
            return new CommonResult<>(444, "failed");
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
