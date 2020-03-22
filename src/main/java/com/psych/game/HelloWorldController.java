package com.psych.game;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/dev_test")
    public String initTest()
    {
        return "Hello world";
    }
}
