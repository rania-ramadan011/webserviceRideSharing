package com.mycompany.ridesharingprows;

import com.mycompany.ridesharingprows.DriverCarInfo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-19T09:06:04")
@StaticMetamodel(Trip.class)
public class Trip_ { 

    public static volatile SingularAttribute<Trip, Float> cost;
    public static volatile SingularAttribute<Trip, String> dayTrip;
    public static volatile SingularAttribute<Trip, String> tripFrom;
    public static volatile SingularAttribute<Trip, Double> startlatitude;
    public static volatile SingularAttribute<Trip, String> tripTo;
    public static volatile SingularAttribute<Trip, String> tpast;
    public static volatile SingularAttribute<Trip, Integer> numberOfSeats;
    public static volatile SingularAttribute<Trip, DriverCarInfo> driverId;
    public static volatile SingularAttribute<Trip, Integer> idTrip;
    public static volatile SingularAttribute<Trip, Double> startlongtiude;
    public static volatile SingularAttribute<Trip, Double> endlatitude;
    public static volatile SingularAttribute<Trip, String> tripName;
    public static volatile SingularAttribute<Trip, String> details;
    public static volatile SingularAttribute<Trip, Double> endlongtiude;
    public static volatile SingularAttribute<Trip, String> tripTime;

}