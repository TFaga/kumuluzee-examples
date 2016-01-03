package com.acme.trains;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BookingService  {

    @Inject
    private ServiceRegistry services;

    private String serviceName = "trains-booking";
    private String endpointURI;

    public BookingService() {
        endpointURI = System.getenv("BASE_URI");
    }

    @PostConstruct
    public void registerService() {

        services.registerService(serviceName, endpointURI);
    }

    @PreDestroy
    public void unregisterService() {

        services.unregisterService(serviceName, endpointURI);
    }
}