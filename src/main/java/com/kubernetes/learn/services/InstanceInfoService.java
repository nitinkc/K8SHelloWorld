package com.kubernetes.learn.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by nichaurasia on Thursday, May/28/2020 at 10:49 AM
 */

@Service

public class InstanceInfoService {

    @Value("${host.name:defaultHostName}")
    private String hostName;

    @Value("${default.env.instance.guid:defaultInstanceGUID}")
    private String instanceGuid;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    private String fullHostName;

    @PostConstruct
    public void init() {
        this.fullHostName = hostName + " :: instance ID : " + instanceGuid;
    }

    public String getFullHostName() {
        return fullHostName;
    }

    public String retrieveInstanceInfo() {
        return hostName.substring(hostName.length()-5);
    }

    public String getAppInfo() {
        return "App: " + appName + ", Version: " + appVersion;
    }

    public String getLocalIpAddress() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress();  // e.g., 192.168.1.5
        } catch (UnknownHostException e) {
            return "Unknown";
        }
    }
}
