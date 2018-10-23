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

    @RequestMapping("/demo4")
    public String demo4() {
        return "demo4";
    }

    @RequestMapping("/demo12")
    public String demo12() {
        return "demo12";
    }

    @RequestMapping("/demo5")
    public String demo5() {
        return "demo5";
    }

    @RequestMapping("/demo6")
    public String demo6() {
        return "demo6";
    }

    @RequestMapping("/demo11")
    public String demo11() {
        return "demo11";
    }

    @RequestMapping("/demo7")
    public String demo7() {
        return "demo7";
    }

    @RequestMapping("/demo8")
    public String demo8() {
        return "demo8";
    }

    @RequestMapping("/dmeo9")
    public String demo9() {
        return "demo9";
    }

    @RequestMapping("/demo10")
    public String demo10() {
        return "demo10";
    }

    @RequestMapping("/demo13")
    public String demo13() {
        return "demo13";
    }




}
