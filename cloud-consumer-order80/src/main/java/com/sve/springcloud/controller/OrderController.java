package com.sve.springcloud.controller;

import com.sve.springcloud.entities.CommonResult;
import com.sve.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController("/comsumer/payment")
public class OrderController {

    private static  final  String PAYMENT_URL = "http://localhost:8001/";
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("create")
    public CommonResult<Payment> create(Payment payment){
        return  restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment,CommonResult.class);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        return  restTemplate.getForObject(PAYMENT_URL + "/payment/get/"+ id,CommonResult.class);
    }
}
