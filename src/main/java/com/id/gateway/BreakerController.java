package com.id.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BreakerController {
    @GetMapping("/inCaseOfFailure")
    public String getErrorMessage(){
        return "please wait  just i will you  provides the true data";
    }
}
