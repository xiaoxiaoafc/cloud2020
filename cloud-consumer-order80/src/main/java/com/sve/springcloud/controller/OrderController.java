package com.sve.springcloud.controller;

import com.sve.springcloud.entitys.CommonResult;
import com.sve.springcloud.entitys.Payment;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {

    private static  final  String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/";
    // private static  final  String PAYMENT_URL = "http://192.168.10.151:8001/";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("comsumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return  restTemplate.postForObject(PAYMENT_URL + "payment/create",payment,CommonResult.class);
    }

    @GetMapping(value = "comsumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        List<String> services =  discoveryClient.getServices();

         System.out.println(services);

          ResponseEntity<CommonResult> responseEntity =  restTemplate.getForEntity(PAYMENT_URL + "payment/get/"+ id,CommonResult.class);
        return  restTemplate.getForObject(PAYMENT_URL + "payment/get/"+ id,CommonResult.class);
    }
}
