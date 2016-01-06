package com.acme.trains;

import com.acme.trains.models.Route;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

@Model
public class RoutesBean {

    @Inject
    private ServiceRegistry services;

    public List<Route> getAllRoutes() {

        return ClientBuilder.newClient()
                .target(services.discoverServiceURI("trains-routes")).path("routes")
                .request().get(new GenericType<List<Route>>() {});
    }
}
