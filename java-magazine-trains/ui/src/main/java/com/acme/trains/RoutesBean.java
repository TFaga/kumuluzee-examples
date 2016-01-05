package com.acme.trains;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;

@Model
public class RoutesBean {

    @Inject
    private ServiceRegistry services;

    public List getAllRoutes() {

        return ClientBuilder.newClient()
                .target(services.discoverServiceURI("trains-routes")).path("routes")
                .request().get(List.class);
    }
}
