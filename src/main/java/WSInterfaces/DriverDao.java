/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WSInterfaces;

import com.mycompany.ridesharingprows.DriverCarInfo;
import com.mycompany.ridesharingprows.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Rania
 */
public interface DriverDao  extends CrudRepository<DriverCarInfo,Integer> {
    @Query("SELECT d.userId FROM DriverCarInfo d WHERE d.driveCarID = ?1")
      User findByDriveCarIDs(int id);
      
      @Query("SELECT u FROM User u WHERE u.idUser = ?1")
      User findByUserIds(int id);
      
      @Query("SELECT d FROM DriverCarInfo d WHERE d.userId = ?1")
      DriverCarInfo findByIdUSer(User id);
      
      
}
