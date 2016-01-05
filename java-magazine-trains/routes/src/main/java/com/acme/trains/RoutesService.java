package com.acme.trains;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Eager
@ApplicationScoped
public class RoutesService {

    @Inject
    private ServiceRegistry services;

    private String serviceName = "trains-routes";
    private String endpointURI;

    public RoutesService() {
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
