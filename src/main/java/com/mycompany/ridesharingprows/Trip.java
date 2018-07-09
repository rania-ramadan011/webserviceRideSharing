/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ridesharingprows;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rania
 */
@Entity
@Table(name = "trip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t")
    , @NamedQuery(name = "Trip.findByIdTrip", query = "SELECT t FROM Trip t WHERE t.idTrip = ?1")
    , @NamedQuery(name = "Trip.findByTripName", query = "SELECT t FROM Trip t WHERE t.tripName = :tripName")
    , @NamedQuery(name = "Trip.findByDetails", query = "SELECT t FROM Trip t WHERE t.details = :details")
    , @NamedQuery(name = "Trip.findByTripTime", query = "SELECT t FROM Trip t WHERE t.tripTime = ?1")
    , @NamedQuery(name = "Trip.findByTripFrom", query = "SELECT t FROM Trip t WHERE t.tripFrom = ?1")
    , @NamedQuery(name = "Trip.findByTripTo", query = "SELECT t FROM Trip t WHERE t.tripTo = ?1")
    , @NamedQuery(name = "Trip.findByNumberOfSeats", query = "SELECT t FROM Trip t WHERE t.numberOfSeats = :numberOfSeats")})
public class Trip implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "startlongtiude")
    private Double startlongtiude;
    @Column(name = "startlatitude")
    private Double startlatitude;
    @Column(name = "endlatitude")
    private Double endlatitude;
    @Column(name = "endlongtiude")
    private Double endlongtiude;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTrip")
    private Integer idTrip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tripName")
    private String tripName;
    @Size(max = 100)
    @Column(name = "details")
    private String details;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ttime")
    private String tripTime;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "tfrom")
    private String tripFrom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "tto")
    private String tripTo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numberOfSeats")
    private int numberOfSeats;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost")
    private float cost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day")
    private String dayTrip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tpast")
    private String tpast;
    @JsonIgnore
    @JoinColumn(name = "driverId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private DriverCarInfo driverId;

    public Trip() {
    }

    public Trip(Integer idTrip) {
        this.idTrip = idTrip;
    }

    public Trip(Integer idTrip, String tripName, String time, String from, String to, int numberOfSeats) {
        this.idTrip = idTrip;
        this.tripName = tripName;
        this.tripTime = time;
        this.tripFrom = from;
        this.tripTo = to;
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(Integer idTrip) {
        this.idTrip = idTrip;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

   public String getTripTime() {
        return tripTime;
    }

    public void setTripTime(String tripTime) {
        this.tripTime = tripTime;
    }

    public String getTripFrom() {
        return tripFrom;
    }

    public void setTripFrom(String tripFrom) {
        this.tripFrom = tripFrom;
    }
    public String getTpast() {
        return tpast;
    }

    public void setTpast(String tpast) {
        this.tpast = tpast;
    }

    public String getTripTo() {
        return tripTo;
    }

    public void setTripTo(String tripTo) {
        this.tripTo = tripTo;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
    public float getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public DriverCarInfo getDriverId() {
        return driverId;
    }

    public void setDriverId(DriverCarInfo driverId) {
        this.driverId = driverId;
    }
     public String getDayTrip() {
        return dayTrip;
    }
    public void setDayTrip(String day) {
        this.dayTrip = day;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrip != null ? idTrip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trip)) {
            return false;
        }
        Trip other = (Trip) object;
        if ((this.idTrip == null && other.idTrip != null) || (this.idTrip != null && !this.idTrip.equals(other.idTrip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.ridesharingprows.Trip[ idTrip=" + idTrip + " ]";
    }

    public Double getStartlongtiude() {
        return startlongtiude;
    }

    public void setStartlongtiude(Double startlongtiude) {
        this.startlongtiude = startlongtiude;
    }

    public Double getStartlatitude() {
        return startlatitude;
    }

    public void setStartlatitude(Double startlatitude) {
        this.startlatitude = startlatitude;
    }

    public Double getEndlatitude() {
        return endlatitude;
    }

    public void setEndlatitude(Double endlatitude) {
        this.endlatitude = endlatitude;
    }

    public Double getEndlongtiude() {
        return endlongtiude;
    }

    public void setEndlongtiude(Double endlongtiude) {
        this.endlongtiude = endlongtiude;
    }
    
}
