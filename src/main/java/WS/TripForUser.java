/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import WSInterfaces.DriverDao;
import WSInterfaces.MyUserDao;
import WSInterfaces.TripDao;
import WSInterfaces.reservationDao;
import com.mycompany.ridesharingprows.Trip;
import com.mycompany.ridesharingprows.TripReservation;
import com.mycompany.ridesharingprows.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Rania
 */
@Controller
public class TripForUser {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    DriverDao driverDao = context.getBean(DriverDao.class);
    MyUserDao userDao = context.getBean(MyUserDao.class);
    TripDao tripDao = context.getBean(TripDao.class);
    reservationDao resDao = context.getBean(reservationDao.class);

    //-----------------------------------deleteReservation.json
    @RequestMapping(value = "deleteReservation.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Trip deleteReservation(@RequestBody List<Integer> tripAndUserID) {

        Trip t = new Trip();
        try {
            resDao.deleteUserReserv(tripAndUserID.get(0), tripAndUserID.get(1));
            t.setTripName("Done");
            return t;

        } catch (Exception e) {
            t.setTripName("Error");
            e.printStackTrace();
            return t;
        }

    }

    @RequestMapping(value = "/getSearchTrips.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Trip> getTrips(@RequestBody Trip trip) {
        List<Trip> t = tripDao.findTripByFromTo(trip.getTripFrom(), trip.getTripTo());
        for (Trip trip1 : t) {
            System.out.println(trip1.getTripName());
        }

        return t;
    }

    @RequestMapping(value = "/getSearchTrips/{from}/{to}.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Trip> getTripsN(@PathVariable(value = "from") String from, @PathVariable(value = "to") String to) {

        List<Trip> t = tripDao.findTripByFromTo(from, to);
        for (Trip trip1 : t) {
            System.out.println(trip1.getTripName());
        }
        return t;
    }

    //get all past trip  to  user
    @RequestMapping(value = "getAllPastTriptoUser.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Trip> getAllPastTriptoUser(@RequestBody User user) {

        List<TripReservation> tripReservList = resDao.findTripsByUserId(user.getIdUser());
        List<Trip> tripList = new ArrayList<>();

        if (tripReservList.size() > 0) {
            for (int i = 0; i < tripReservList.size(); i++) {

                Trip trip = tripDao.findByIdTrip(tripReservList.get(i).getTripReservationPK().getTripId());

                if (!trip.getTpast().equals('f')) {
                    tripList.add(trip);
                }

            }
        }

        return tripList;
    }

    //get all  trip That user Reservers incomming trip for user
    @RequestMapping(value = "getReserveredTrip.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Trip> getAllUserReserveredTrip(@RequestBody User user) {

        List<TripReservation> tripReservList = resDao.findTripsByUserId(user.getIdUser());
        List<Trip> tripList = new ArrayList<>();

        if (tripReservList.size() > 0) {
            for (int i = 0; i < tripReservList.size(); i++) {

                Trip trip = tripDao.findByIdTrip(tripReservList.get(i).getTripReservationPK().getTripId());
                System.out.println("ggggggggggggggggggggggggggggggggggggggggggggggggggggggg");

                if (!trip.getTpast().equals('t')) {
                    tripList.add(trip);
                }

            }
        }

        return tripList;
    }

}
