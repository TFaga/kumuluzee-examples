package com.acme.trains;

import com.acme.trains.models.Route;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

/**
 * <p>Manages routes for the UI via the REST interface from the routes microservice.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@Model
public class RoutesBean {

    @Inject
    private ServiceRegistry services;

    /**
     * <p>Retrieves the list of all available routes.</p>
     *
     * @return List of all available routes.
     */
    public List<Route> getAllRoutes() {

        return ClientBuilder.newClient()
                .target(services.discoverServiceURI("trains-routes")).path("routes")
                .request().get(new GenericType<List<Route>>() {});
    }
}
