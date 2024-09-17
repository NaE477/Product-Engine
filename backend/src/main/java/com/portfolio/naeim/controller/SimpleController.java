package com.portfolio.naeim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

    @GetMapping("/public/simple-page")
    @ResponseBody
    public String showSimplePage() {
        long number = 1478;
        long i = 10;
        long sum = 0;
        long numOfDigits = 0;
        while (number > 0) {
            long yekan = number % 10;
            sum = yekan + sum;
            numOfDigits++;
            number = number / i;
        }
        return "<html><body><h1>Welcome to the Simple Page</h1></body></html>" + sum + "-" + numOfDigits;
    }
}
