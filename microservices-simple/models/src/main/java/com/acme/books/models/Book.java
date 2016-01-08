package com.acme.books.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * <p>JPA class representing the Book entity and table in the database.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@Entity
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private String author;

    @OneToMany(mappedBy="book")
    private List<BookOrder> bookOrders;

    /**
     * <p>Gets the value of id and returns id.</p>
     *
     * @return Value of id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * <p>Sets the id.</p>
     *
     * <p>You can use getId() to get the value of id.</p>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <p>Gets the value of title and returns title.</p>
     *
     * @return Value of title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * <p>Sets the title.</p>
     *
     * <p>You can use getTitle() to get the value of title.</p>
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * <p>Gets the value of description and returns description.</p>
     *
     * @return Value of description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * <p>Sets the description.</p>
     *
     * <p>You can use getDescription() to get the value of description.</p>
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <p>Gets the value of author and returns author.</p>
     *
     * @return Value of author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * <p>Sets the author.</p>
     *
     * <p>You can use getAuthor() to get the value of author.</p>
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * <p>Gets the value of bookOrders and returns bookOrders.</p>
     *
     * @return Value of bookOrders.
     */
    public List<BookOrder> getBookOrders() {
        return bookOrders;
    }

    /**
     * <p>Sets the bookOrders.</p>
     *
     * <p>You can use getBookOrders() to get the value of bookOrders.</p>
     */
    public void setBookOrders(List<BookOrder> bookOrders) {
        this.bookOrders = bookOrders;
    }
}
