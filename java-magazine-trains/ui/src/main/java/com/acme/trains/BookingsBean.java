package com.acme.trains;

import com.acme.trains.models.Booking;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

@Model
public class BookingsBean {

    @Inject
    private ServiceRegistry services;

    public List<Booking> getAllBookings() {

        return ClientBuilder.newClient()
                .target(services.discoverServiceURI("trains-booking")).path("bookings")
                .request().get(new GenericType<List<Booking>>() {});
    }
}