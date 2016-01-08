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
 * <p>JPA class representing the Route entity and table in the database.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 */
@Entity
@NamedQuery(name="Route.findAll", query="SELECT r FROM Route r")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String startPlace;

    private String endPlace;

    @Temporal(TemporalType.TIMESTAMP)
    private Date duration;

    @ManyToMany(mappedBy = "routes")
    private List<Booking> bookings;

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
     * <p>Gets the value of name and returns name.</p>
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Sets the name.</p>
     *
     * <p>You can use getName() to get the value of name.</p>
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>Gets the value of startPlace and returns startPlace.</p>
     *
     * @return Value of startPlace.
     */
    public String getStartPlace() {
        return startPlace;
    }

    /**
     * <p>Sets the startPlace.</p>
     *
     * <p>You can use getStartPlace() to get the value of startPlace.</p>
     */
    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    /**
     * <p>Gets the value of endPlace and returns endPlace.</p>
     *
     * @return Value of endPlace.
     */
    public String getEndPlace() {
        return endPlace;
    }

    /**
     * <p>Sets the endPlace.</p>
     *
     * <p>You can use getEndPlace() to get the value of endPlace.</p>
     */
    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    /**
     * <p>Gets the value of duration and returns duration.</p>
     *
     * @return Value of duration.
     */
    public Date getDuration() {
        return duration;
    }

    /**
     * <p>Sets the duration.</p>
     *
     * <p>You can use getDuration() to get the value of duration.</p>
     */
    public void setDuration(Date duration) {
        this.duration = duration;
    }

    /**
     * <p>Gets the value of bookings and returns bookings.</p>
     *
     * @return Value of bookings.
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * <p>Sets the bookings.</p>
     *
     * <p>You can use getBookings() to get the value of bookings.</p>
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}