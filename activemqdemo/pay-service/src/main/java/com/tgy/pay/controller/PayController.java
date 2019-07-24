package com.tgy.pay.controller;

import com.tgy.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Service;

/**
 * @author DragonSwimDiving
 * @program activemqdemo
 * @Date 2019-07-12 10:03
 **/
@RestController
public class PayController {
    @Autowired
    private PayService service;


    @GetMapping("/index")
    public void testHigh(){
        for (int i=0;i<20;i++){
            new Thread(new Request()).start();
        }
        String s = service.getDataBaseContection(10);
        try {
            Thread.currentThread().sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/test")
    public void test(){
        String s = service.getDataBaseContection(10);
        System.out.println("--测试结果："+s);
    }
    public class Request implements Runnable{

        @Override
        public void run() {

        }
    }

}
