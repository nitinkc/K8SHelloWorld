package com.kubernetes.k8sHelloWorld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;


/**
 * Created by nichaurasia on Wednesday, May/27/2020 at 9:30 PM
 */

@RestController
public class HelloWorldController {
    private static final String DEFAULT_ENV_INSTANCE_GUID = "HOSTNAME" ;
    private static final String HOST_NAME = "LOCAL";

    @Autowired
    Environment env;

    // @Value(${ENVIRONMENT_VARIABLE_NAME:DEFAULT_VALUE})
    @Value("${" + HOST_NAME + ":" + DEFAULT_ENV_INSTANCE_GUID + "}")
    private String hostName;

    @GetMapping(path = "/")
    public String imUpAndRunning() {
        String port = env.getProperty("local.server.port");
        String heath_check = "The Server is up and running on port : " + port + " with HostName : ";

        return heath_check + hostName;
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World " + " V3 " ;
    }
}
