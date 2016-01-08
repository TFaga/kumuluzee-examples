package com.acme.trains.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>JPA class representing the Booking entity and table in the database.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@Entity
@NamedQuery(name="Booking.findAll", query="SELECT b FROM Booking b")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @ManyToMany
    private List<Route> routes;

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
     * <p>Gets the value of routes and returns routes.</p>
     *
     * @return Value of routes.
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * <p>Sets the routes.</p>
     *
     * <p>You can use getRoutes() to get the value of routes.</p>
     */
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}