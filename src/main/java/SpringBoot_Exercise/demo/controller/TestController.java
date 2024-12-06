package SpringBoot_Exercise.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


//    @RequestMapping("/")
//    public String index() {
//        return "hello world2222";
//    }

    @RequestMapping("/jump")
    public String jump() {
        return "점프 투 스프링";
    }

    @GetMapping("/sbb")
    public void sbb() {
        System.out.println("index");
    }
}
