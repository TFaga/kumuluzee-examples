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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String start) {
        this.startPlace = start;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String end) {
        this.endPlace = end;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}