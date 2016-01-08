package com.acme.books.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>JPA class representing the BookOrder entity and table in the database.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@Entity
@NamedQuery(name="BookOrder.findAll", query="SELECT o FROM BookOrder o")
public class BookOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

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
     * <p>Gets the value of orderDate and returns orderDate.</p>
     *
     * @return Value of orderDate.
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * <p>Sets the orderDate.</p>
     *
     * <p>You can use getOrderDate() to get the value of orderDate.</p>
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * <p>Gets the value of book and returns book.</p>
     *
     * @return Value of book.
     */
    public Book getBook() {
        return book;
    }

    /**
     * <p>Sets the book.</p>
     *
     * <p>You can use getBook() to get the value of book.</p>
     */
    public void setBook(Book book) {
        this.book = book;
    }
}