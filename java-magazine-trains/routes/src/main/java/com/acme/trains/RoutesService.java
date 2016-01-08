package com.acme.trains;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * <p>Manages the lifecycle of the routes service endpoint in the service registry.</p>
 *
 * <p>This bean is started on application load.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@Eager
@ApplicationScoped
public class RoutesService {

    @Inject
    private ServiceRegistry services;

    private String serviceName = "trains-routes";
    private String endpointURI;

    /**
     * <p>Retrieves the routes service base URI from the 'BASE_URI' environment variable.</p>
     */
    public RoutesService() {
        endpointURI = System.getenv("BASE_URI");
    }

    /**
     * <p>Registers the routes service endpoint with the service registry.</p>
     */
    @PostConstruct
    public void registerService() {

        services.registerService(serviceName, endpointURI);
    }

    /**
     * <p>Unregisters the routes service endpoint with the service registry.</p>
     */
    @PreDestroy
    public void unregisterService() {

        services.unregisterService(serviceName, endpointURI);
    }
}
