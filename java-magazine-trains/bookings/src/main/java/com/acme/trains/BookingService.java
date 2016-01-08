package com.acme.trains;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * <p>Manages the lifecycle of the booking service endpoint in the service registry.</p>
 *
 * <p>This bean is started on application load.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@Eager
@ApplicationScoped
public class BookingService  {

    @Inject
    private ServiceRegistry services;

    private String serviceName = "trains-booking";
    private String endpointURI;

    /**
     * <p>Retrieves the booking service base URI from the 'BASE_URI' environment variable.</p>
     */
    public BookingService() {
        endpointURI = System.getenv("BASE_URI");
    }

    /**
     * <p>Registers the booking service endpoint with the service registry.</p>
     */
    @PostConstruct
    public void registerService() {

        services.registerService(serviceName, endpointURI);
    }

    /**
     * <p>Unregisters the booking service endpoint with the service registry.</p>
     */
    @PreDestroy
    public void unregisterService() {

        services.unregisterService(serviceName, endpointURI);
    }
}