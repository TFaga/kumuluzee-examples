package com.acme.books;

import com.acme.books.models.Book;
import com.acme.books.models.BookOrder;

import java.util.Date;

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
import javax.ws.rs.core.Response;;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class OrdersResource {

    @PersistenceContext(unitName = "books")
    private EntityManager em;

    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam("id") Integer id) {

        BookOrder o = em.find(BookOrder.class, id);

        if (o == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(o).build();
    }

    @POST
    public Response placeOrder(Book b) {

        BookOrder o = new BookOrder();
        o.setBook(b);
        o.setOrderDate(new Date());

        em.getTransaction().begin();

        em.persist(o);

        em.getTransaction().commit();

        return Response.status(Response.Status.CREATED).entity(o).build();
    }
}
