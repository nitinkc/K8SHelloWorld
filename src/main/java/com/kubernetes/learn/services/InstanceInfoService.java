package com.kubernetes.learn.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by nichaurasia on Thursday, May/28/2020 at 10:49 AM
 */

@Service

public class InstanceInfoService {

    @Value("${host.name:defaultHostName}")
    private String hostName;

    @Value("${default.env.instance.guid:defaultInstanceGUID}")
    private String instanceGuid;

    private String fullHostName;

    @PostConstruct
    public void init() {
        this.fullHostName = hostName + " \n instance ID : " + instanceGuid;
    }

    public String getFullHostName() {
        return fullHostName;
    }

    public String retrieveInstanceInfo() {
        return hostName.substring(hostName.length()-5);
    }
}
