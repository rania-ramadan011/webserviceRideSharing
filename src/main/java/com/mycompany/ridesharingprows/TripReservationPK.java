/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ridesharingprows;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Rania
 */
@Embeddable
public class TripReservationPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "trip_id")
    private int tripId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;

    public TripReservationPK() {
    }

    public TripReservationPK(int tripId, int userId) {
        this.tripId = tripId;
        this.userId = userId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tripId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TripReservationPK)) {
            return false;
        }
        TripReservationPK other = (TripReservationPK) object;
        if (this.tripId != other.tripId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.ridesharingprows.TripReservationPK[ tripId=" + tripId + ", userId=" + userId + " ]";
    }
    
}
