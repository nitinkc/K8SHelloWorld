package com.kubernetes.k8sHelloWorld.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by nichaurasia on Thursday, May/28/2020 at 10:49 AM
 */

@Service

public class InstanceInfoService {

    private static final String HOST_NAME = "HOSTNAME";

    private static final String DEFAULT_ENV_INSTANCE_GUID = "LOCAL";

    // @Value(${ENVIRONMENT_VARIABLE_NAME:DEFAULT_VALUE})
    @Value("${" + HOST_NAME + ":" + DEFAULT_ENV_INSTANCE_GUID + "}")
    private String hostName;

    public String retrieveInstanceInfo() {
        return hostName.substring(hostName.length()-5);
    }
}
