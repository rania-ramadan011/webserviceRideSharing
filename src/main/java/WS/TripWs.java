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
import com.mycompany.ridesharingprows.DriverCarInfo;
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
public class TripWs {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    DriverDao driverDao = context.getBean(DriverDao.class);
    MyUserDao userDao = context.getBean(MyUserDao.class);
    TripDao tripDao = context.getBean(TripDao.class);
    reservationDao resDao = context.getBean(reservationDao.class);

    //----------------------------------------------------------------------------------
    @RequestMapping(value = "/getTrip/{tripId}.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Trip getCarInfo(@PathVariable(value = "tripId") int tripId) {

        Trip t = tripDao.findByIdTrip(tripId);
        return t;

    }
    //-------------------------------------------------------------------------------------   

    @RequestMapping(value = "getDriverInfo.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    User getDriverInfo(@RequestBody Trip trip) {
        Trip t = tripDao.findByIdTrip(trip.getIdTrip());
        return t.getDriverId().getUserId();
    }

    //-------------------------------------------------------------------------------------   
    @RequestMapping(value = "addTrip.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Trip addTrip(@RequestBody List<Trip> vals) {
        
        Trip u = (Trip) vals.get(0);//t2
        Trip t = (Trip) vals.get(1);
        User user = userDao.findByIdUser(u.getIdTrip());
        t.setDriverId(user.getDriverCarInfo());
        try {
            t.setTpast("f");
            Trip tt = tripDao.save(t);
            u.setIdTrip(tt.getIdTrip());
            u.setTripTo("Done");
            System.out.print("trip added");
            return u;
        } catch (Exception e) {
            u.setTripTo("Error in Saving Object");
            return u;
        }
    }

    //-------------------------------------------------------------------------------------   
    @RequestMapping(value = "registerWithTrip.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    User registerWithTrip(@RequestBody List<Integer> values) {

        User u = new User();
        if (resDao.findByTripIdAndUserId(values.get(1), values.get(0)) != null) {
            u.setEMail("your Are Already register for This Trip");
            return u;
        }

        if (tripDao.checkForAvailableSeats(values.get(1)) > 0) {
            TripReservation t = new TripReservation(values.get(1), values.get(0));
            resDao.save(t);

            tripDao.updateSeats(values.get(1));

            System.out.println("DONEEEEEe");
            u.setEMail("t");
            return u;
        } else {
            u.setEMail("f");
            return u;
        }

    }
    //----------------------------------------------------------------------------------

    @RequestMapping(value = "setTripToBePast.json", method = RequestMethod.POST)
    public @ResponseBody
    Void getDriverInfo(@RequestBody Integer id) {
        System.out.println("inside update Trip");
        tripDao.updateTripToBePast(id);

        return null;
    }
    //----------------------------------------------------------------------------------

    @RequestMapping(value = "getTrip.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Trip getTrip(@RequestBody Trip trip) {
        System.out.println("inside Get Trip");
        Trip t = tripDao.findByIdTrip(trip.getIdTrip());
        return t;
    }
    //----------------------------------------------------------------------------------

    @RequestMapping(value = "getReservedUsers.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<User> getReservedUSers(@RequestBody Trip trip) {

        List<Integer> usersIDs = resDao.getReservedUsers(trip.getIdTrip());

        List<User> users = new ArrayList<>();
        for (Integer userId : usersIDs) {
            users.add(userDao.findByIdUser(userId));
        }

        return users;
    }

    //get Past trips  for Deriver 
    @RequestMapping(value = "getAllPastTripToDerviver.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<Trip> getAllpastTripToDriver(@RequestBody User user) {
        List<Trip> tripsList = tripDao.findAllPastTrip();
        List<Trip> pastTripList = new ArrayList<>();
        for (Trip trip : tripsList) {

            if (trip.getDriverId().equals(user.getDriverCarInfo())) {

                pastTripList.add(trip);
            }
        }
        return pastTripList;
    }
    //--------------------------Get Trip ----------------------------
     @RequestMapping(value = "getTripById.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Trip getCarInfo(@RequestBody Integer id) {
        Trip t = tripDao.findByIdTrip(id);
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+t.getTripName());
        return t;

    }
    
//    //-----------------------------------deleteReservation.json
//    @RequestMapping(value = "deleteReservation.json", method = RequestMethod.POST, produces = "application/json")
//    public @ResponseBody
//    Trip deleteReservation(@RequestBody List<Integer> tripAndUserID) {
//
//        Trip t = new Trip();
//        try {
//            resDao.deleteUserReserv(tripAndUserID.get(0), tripAndUserID.get(1));
//            t.setTripName("Done");
//            return t;
//
//        } catch (Exception e) {
//            t.setTripName("Error");
//            e.printStackTrace();
//            return t;
//        }
//
//    }
//
//    @RequestMapping(value = "/getSearchTrips.json", method = RequestMethod.POST, produces = "application/json")
//    public @ResponseBody
//    List<Trip> getTrips(@RequestBody Trip trip) {
//        List<Trip> t = tripDao.findTripByFromTo(trip.getTripFrom(), trip.getTripTo());
//        for (Trip trip1 : t) {
//            System.out.println(trip1.getTripName());
//        }
//
//        return t;
//    }
//
//    @RequestMapping(value = "/getSearchTrips/{from}/{to}.json", method = RequestMethod.GET, produces = "application/json")
//    public @ResponseBody
//    List<Trip> getTripsN(@PathVariable(value = "from") String from, @PathVariable(value = "to") String to) {
//
//        List<Trip> t = tripDao.findTripByFromTo(from, to);
//        for (Trip trip1 : t) {
//            System.out.println(trip1.getTripName());
//        }
//        return t;
//    }
//
//    //get all past trip  to  user
//    @RequestMapping(value = "getAllPastTriptoUser.json", method = RequestMethod.POST, produces = "application/json")
//    public @ResponseBody
//    List<Trip> getAllPastTriptoUser(@RequestBody User user) {
//
//        List<TripReservation> tripReservList = resDao.findTripsByUserId(user.getIdUser());
//        List<Trip> tripList = new ArrayList<>();
//
//        if (tripReservList.size() > 0) {
//            for (int i = 0; i < tripReservList.size(); i++) {
//
//                Trip trip = tripDao.findByIdTrip(tripReservList.get(i).getTripReservationPK().getTripId());
//
//                if (!trip.getTpast().equals('f')) {
//                    tripList.add(trip);
//                }
//
//            }
//        }
//
//        return tripList;
//    }
//
//    //get all  trip That user Reservers incomming trip for user
//    @RequestMapping(value = "getReserveredTrip.json", method = RequestMethod.POST, produces = "application/json")
//    public @ResponseBody
//    List<Trip> getAllUserReserveredTrip(@RequestBody User user) {
//
//        List<TripReservation> tripReservList = resDao.findTripsByUserId(user.getIdUser());
//        List<Trip> tripList = new ArrayList<>();
//
//        if (tripReservList.size() > 0) {
//            for (int i = 0; i < tripReservList.size(); i++) {
//
//                Trip trip = tripDao.findByIdTrip(tripReservList.get(i).getTripReservationPK().getTripId());
//                System.out.println("ggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
//
//                if (!trip.getTpast().equals('t')) {
//                    tripList.add(trip);
//                }
//
//            }
//        }
//
//        return tripList;
//    }
//

}
