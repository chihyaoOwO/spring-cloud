package com.chihyao.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "\tpaymentInfo_OK，id：" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber = 5;
//        int i = 1 / 0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "線程池：" + Thread.currentThread().getName() +
                "\tpaymentInfo_TimeOut，id：" + id + "，耗時："  + timeNumber + "秒";
    }
    public String paymentInfo_TimeOutHandler(Integer id){
        return "8001提供者，線程池：" + Thread.currentThread().getName() +
                "\tpaymentInfo_TimeOutHandler系统繁忙，請稍後再試，id：" + id;
    }

    //================熔斷
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否開啟斷路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 請求次數(超過10次)
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 時間窗口期(10秒內)
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 失敗率達到多少後跳閘
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不可為負數");
        }
        return Thread.currentThread().getName() + "\t調用成功，流水號：" + UUID.randomUUID().toString();
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不可為負數，請稍後再試";
    }
}
