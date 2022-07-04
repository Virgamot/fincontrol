package ru.fincontrol.model;

public enum SourceType {
    CBRF("cbrf");

    String serviceName;

    SourceType(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }
}
