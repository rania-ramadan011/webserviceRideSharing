/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WSInterfaces;

import com.mycompany.ridesharingprows.Trip;
import com.mycompany.ridesharingprows.TripReservation;
import com.mycompany.ridesharingprows.User;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TECHNOLOGY CITY
 */
public interface reservationDao  extends CrudRepository<TripReservation,Integer>{
    
    @Query("SELECT t FROM TripReservation t WHERE t.tripReservationPK.tripId = ?1 and t.tripReservationPK.userId = ?2")
    TripReservation findByTripIdAndUserId(int TripID , int UserID);
    
    //Find Trips By User Id
    @Query("SELECT t FROM TripReservation t WHERE t.tripReservationPK.userId = ?1")
     List<TripReservation> findTripsByUserId(int UserID);
    
    @Query("SELECT t.tripReservationPK.userId FROM TripReservation t WHERE t.tripReservationPK.tripId = ?1")
    List<Integer> getReservedUsers(int TripID);  
    
    @Modifying
    @Transactional
    @Query("delete TripReservation t WHERE t.tripReservationPK.tripId = ?1 and t.tripReservationPK.userId = ?2")
    public void deleteUserReserv(int tripId , int userID);
}
