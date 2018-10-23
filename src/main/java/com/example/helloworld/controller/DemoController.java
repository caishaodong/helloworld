package com.example.helloworld.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("/demo")
public class DemoController {
    RateLimiter limiter = RateLimiter.create(10);

    @RequestMapping("/demo1")
    public String demo1() {
        int count = 20;
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                doRequest();
            }, "t" + i).start();
        }

        countDownLatch.countDown();

        return "success!";
    }


    private void doRequest() {
        if (limiter.tryAcquire()) {
            System.out.println(Thread.currentThread().getName() + "获取成功");
        } else {
            System.out.println(Thread.currentThread().getName() + "获取失败");
        }
    }


    @RequestMapping("/demo2")
    public String demo2() {
        return "demo2";
    }

    @RequestMapping("demo3")
    public String demo3() {
        return "demo3";
    }

    @RequestMapping("demo4")
    public String demo4() {
        return "demo4";
    }



}
