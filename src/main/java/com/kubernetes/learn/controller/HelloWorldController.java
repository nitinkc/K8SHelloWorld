package com.kubernetes.learn.controller;

import com.kubernetes.learn.HealthStatus;
import com.kubernetes.learn.services.InstanceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by nichaurasia on Wednesday, May/27/2020 at 9:30 PM
 */

@RestController
public class HelloWorldController {


    @Autowired
    Environment env;
    @Autowired
    InstanceInfoService iis;

    @GetMapping(path = "/")
    public Record imUpAndRunning() {
        String port = env.getProperty("local.server.port");
        String heath_check = "The Server is up and running on port : " + port;

        return new HealthStatus(
                heath_check,
                iis.getFullHostName(),
                iis.getAppInfo(),
                iis.getLocalIpAddress()
        );
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hi " + iis.getAppInfo();
    }
}
