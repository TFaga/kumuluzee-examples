package com.acme.trains;

import com.acme.trains.models.Route;

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
 * <p>Contains JAX-RS interface and business logic for routes.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@Path("/routes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class RoutesResource {

    @PersistenceContext(unitName = "trains")
    private EntityManager em;

    /**
     * <p>Queries the database and returns a list of all available routes.</p>
     *
     * @return Response object containing the retrieved list of routes from the database.
     */
    @GET
    public Response getRoutes() {

        List<Route> routes = em.createNamedQuery("Route.findAll", Route.class).getResultList();

        return Response.ok(routes).build();
    }

    /**
     * <p>Queries the database and returns a specific route based on the given id.</p>
     *
     * @param id The id of the wanted route.
     * @return Response object containing the requested route, or empty with the NOT_FOUND status.
     */
    @GET
    @Path("/{id}")
    public Response getRoute(@PathParam("id") Integer id) {

        Route r = em.find(Route.class, id);

        if (r == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(r).build();
    }

    /**
     * <p>Inserts the provided route into the database.</p>
     *
     * @param r The route object which will be created.
     * @return Response object containing the created route.
     */
    @POST
    public Response createRoute(Route r) {

        em.getTransaction().begin();

        em.persist(r);

        em.getTransaction().commit();

        return Response.status(Response.Status.CREATED).entity(r).build();
    }
}
