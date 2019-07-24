package com.tgy.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author DragonSwimDiving
 * @program activemqdemo
 * @Date 2019-07-12 11:28
 **/
@RestController
public class OderConttoller {

    @GetMapping("/get")
    public void testHigh(){
        ThreadTrain threadTrain = new ThreadTrain();
        Thread t1 = new Thread(threadTrain, "窗口1");
        Thread t2 = new Thread(threadTrain, "窗口2");
    }

    class ThreadTrain implements Runnable {
        private int trainCount = 20;

        public void run() {
            while (trainCount>2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    sale();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            try {
                sale();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        //public synchronized  void sale() {
        public  void sale() throws MalformedURLException {
            if (trainCount > 0) {
                URL url = new URL("http://localhost:8080/index");
                System.out.println(trainCount+"--测试结果："+trainCount);
                trainCount--;
            }
        }
    }
}
