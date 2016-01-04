package com.acme.trains;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;

@Model
public class BookingsBean {

    @Inject
    private ServiceRegistry services;

    public List getAllBookings() {

        return ClientBuilder.newClient()
                .target(services.discoverServiceURI("trains-booking")).path("bookings")
                .request().get(List.class);
    }
}