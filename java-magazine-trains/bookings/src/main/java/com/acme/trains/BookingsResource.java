package com.acme.trains;

import com.acme.trains.models.Booking;

import java.awt.print.Book;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <p>Contains JAX-RS interface and business logic for bookings.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class BookingsResource {

    @PersistenceContext(unitName = "trains")
    private EntityManager em;

    /**
     * <p>Queries the database and returns a list of all available bookings.</p>
     *
     * @return Response object containing the retrieved list of booking from the database.
     */
    @GET
    public Response getBookings() {

        List<Booking> bookings = em.createNamedQuery("Booking.findAll", Booking.class).getResultList();

        return Response.ok(bookings).build();
    }

    /**
     * <p>Queries the database and returns a specific booking based on the given id.</p>
     *
     * @param id The id of the wanted booking.
     * @return Response object containing the requested booking, or empty with the NOT_FOUND status.
     */
    @GET
    @Path("/{id}")
    public Response getBooking(@PathParam("id") Integer id) {

        Booking o = em.find(Booking.class, id);

        if (o == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(o).build();
    }

    /**
     * <p>Inserts the provided booking into the database.</p>
     *
     * @param b The booking object which will be created.
     * @return Response object containing the created booking.
     */
    @POST
    public Response createBooking(Booking b) {

        em.getTransaction().begin();

        em.persist(b);

        em.getTransaction().commit();

        return Response.status(Response.Status.CREATED).entity(b).build();
    }
}