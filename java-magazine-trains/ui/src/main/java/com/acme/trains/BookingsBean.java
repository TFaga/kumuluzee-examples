package com.acme.trains;

import com.acme.trains.models.Booking;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

/**
 * <p>Manages bookings for the UI via the REST interface from the bookings microservice.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@Model
public class BookingsBean {

    @Inject
    private ServiceRegistry services;

    /**
     * <p>Retrieves the list of all available bookings.</p>
     *
     * @return List of all available bookings.
     */
    public List<Booking> getAllBookings() {

        return ClientBuilder.newClient()
                .target(services.discoverServiceURI("trains-booking")).path("bookings")
                .request().get(new GenericType<List<Booking>>() {});
    }
}