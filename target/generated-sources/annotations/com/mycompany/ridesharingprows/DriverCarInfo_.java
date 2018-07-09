package com.mycompany.ridesharingprows;

import com.mycompany.ridesharingprows.Trip;
import com.mycompany.ridesharingprows.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-19T09:06:04")
@StaticMetamodel(DriverCarInfo.class)
public class DriverCarInfo_ { 

    public static volatile SingularAttribute<DriverCarInfo, String> carBrand;
    public static volatile SingularAttribute<DriverCarInfo, String> ownername;
    public static volatile CollectionAttribute<DriverCarInfo, Trip> tripCollection;
    public static volatile SingularAttribute<DriverCarInfo, String> licenseEndDate;
    public static volatile SingularAttribute<DriverCarInfo, User> userId;
    public static volatile SingularAttribute<DriverCarInfo, Integer> carYear;
    public static volatile SingularAttribute<DriverCarInfo, String> driverLicenseNum;
    public static volatile SingularAttribute<DriverCarInfo, String> carColor;
    public static volatile SingularAttribute<DriverCarInfo, Integer> carCC;
    public static volatile SingularAttribute<DriverCarInfo, String> licenseIdPhoto;
    public static volatile SingularAttribute<DriverCarInfo, String> nationalidPhoto;
    public static volatile SingularAttribute<DriverCarInfo, String> ownerAddress;
    public static volatile SingularAttribute<DriverCarInfo, Integer> driveCarID;
    public static volatile SingularAttribute<DriverCarInfo, String> carModel;
    public static volatile SingularAttribute<DriverCarInfo, String> status;

}