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

/**
 * <p>Contains JAX-RS interface and business logic for the books.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class BooksResource {

    @PersistenceContext(unitName = "books")
    private EntityManager em;

    /**
     * <p>Queries the database and returns a list of all books.</p>
     *
     * @return Response object containing the retrieved list of books from the database.
     */
    @GET
    public Response getBooks() {

        TypedQuery<Book> query = em.createNamedQuery("Book.findAll", Book.class);

        List<Book> books = query.getResultList();

        return Response.ok(books).build();
    }

    /**
     * <p>Queries the database and returns a specific book based on the given id.</p>
     *
     * @param id The id of the wanted book.
     * @return Response object containing the requested book, or empty with the NOT_FOUND status.
     */
    @GET
    @Path("/{id}")
    public Response getBook(@PathParam("id") Integer id) {

        Book b = em.find(Book.class, id);

        if (b == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(b).build();
    }

    /**
     * <p>Inserts the provided book into the database.</p>
     *
     * @param b The book object which will be created.
     * @return Response object containing the created book.
     */
    @POST
    public Response createBook(Book b) {

        b.setId(null);

        em.getTransaction().begin();

        em.persist(b);

        em.getTransaction().commit();

        return Response.status(Response.Status.CREATED).entity(b).build();
    }
}