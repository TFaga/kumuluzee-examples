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

@Path("/routes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class RoutesResource {

    @PersistenceContext(unitName = "trains")
    private EntityManager em;

    @GET
    public Response getRoutes() {

        List<Route> routes = em.createNamedQuery("Route.findAll", Route.class).getResultList();

        return Response.ok(routes).build();
    }

    @GET
    @Path("/{id}")
    public Response getRoute(@PathParam("id") Integer id) {

        Route r = em.find(Route.class, id);

        if (r == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(r).build();
    }

    @POST
    public Response createRoute(Route r) {

        em.getTransaction().begin();

        em.persist(r);

        em.getTransaction().commit();

        return Response.status(Response.Status.CREATED).entity(r).build();
    }
}
