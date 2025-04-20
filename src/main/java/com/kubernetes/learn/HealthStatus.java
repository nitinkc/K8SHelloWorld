package com.kubernetes.learn;

public record HealthStatus(String status, String hostname, String appInfo, String ipAddress) {}