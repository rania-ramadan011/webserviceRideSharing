/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ridesharingprows;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rania
 */
@Entity
@Table(name = "trip_reservation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TripReservation.findAll", query = "SELECT t FROM TripReservation t")
    , @NamedQuery(name = "TripReservation.findByTripId", query = "SELECT t FROM TripReservation t WHERE t.tripReservationPK.tripId = ?1")
    , @NamedQuery(name = "TripReservation.findByUserId", query = "SELECT t FROM TripReservation t WHERE t.tripReservationPK.userId = ?1")})
public class TripReservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TripReservationPK tripReservationPK;

    public TripReservation() {
    }

    public TripReservation(TripReservationPK tripReservationPK) {
        this.tripReservationPK = tripReservationPK;
    }

    public TripReservation(int tripId, int userId) {
        this.tripReservationPK = new TripReservationPK(tripId, userId);
    }

    public TripReservationPK getTripReservationPK() {
        return tripReservationPK;
    }

    public void setTripReservationPK(TripReservationPK tripReservationPK) {
        this.tripReservationPK = tripReservationPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripReservationPK != null ? tripReservationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TripReservation)) {
            return false;
        }
        TripReservation other = (TripReservation) object;
        if ((this.tripReservationPK == null && other.tripReservationPK != null) || (this.tripReservationPK != null && !this.tripReservationPK.equals(other.tripReservationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.ridesharingprows.TripReservation[ tripReservationPK=" + tripReservationPK + " ]";
    }
    
}
