package com.acme.books;

import com.acme.books.models.Book;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class BooksResource {

    @PersistenceContext(unitName = "books")
    private EntityManager em;

    @GET
    public Response getBooks() {

        TypedQuery<Book> query = em.createNamedQuery("Book.findAll", Book.class);

        List<Book> books = query.getResultList();

        return Response.ok(books).build();
    }

    @GET
    @Path("/{id}")
    public Response getBook(@PathParam("id") Integer id) {

        Book b = em.find(Book.class, id);

        return Response.ok(b).build();
    }

    @POST
    public Response createBook(Book b) {

        b.setId(null);

        em.getTransaction().begin();

        em.persist(b);

        em.getTransaction().commit();

        return Response.status(Response.Status.CREATED).entity(b).build();
    }
}