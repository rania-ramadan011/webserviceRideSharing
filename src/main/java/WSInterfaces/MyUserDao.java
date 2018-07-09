/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WSInterfaces;

import com.mycompany.ridesharingprows.DriverCarInfo;
import com.mycompany.ridesharingprows.User;
import com.mycompany.ridesharingprows.User;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TECHNOLOGY CITY
 */
@Entity
public interface MyUserDao extends CrudRepository<User,Integer>{
    
    //RaniaWork
    User findByEMail(String email);
     //----------------------------------------------*
    User findByIdUser(int idUser);
    //-----------------------------------------------*
    @Query("SELECT u FROM User u WHERE u.eMail = ?1 and u.password = ?2")
    User findByEMailAndPassword(String email,String password);
    //-----------------------------------------------*
     @Query("SELECT u FROM User u")
     List <User> findAllUsers();
    //-----------------------------------------------*
   
    
    //Mostafa Work
  
    User findByUserName(String name);

    @Query("SELECT u FROM User u WHERE u.userName = ?1 and u.password = ?2 and u.pending = 2")
    User LoginAdmin(String userName, String Password);

    @Query("SELECT u FROM User u WHERE u.pending = 0")
    List<User> getAllPendingUsers();

    @Query("SELECT u FROM User u WHERE u.pending = -1")
    List<User> getAllDenyUsers();

    @Modifying
    @Transactional
    @Query("update User u set u.pending = 1 where u.idUser = ?1")
    public void acceptUser(int id);

    @Modifying
    @Transactional
    @Query("update User u set u.pending = -1 where u.idUser = ?1")
    public void denyUser(int id);
    //------------------------------------------------------------*
    
    @Query("SELECT u.driverCarInfo from User u where u.idUser = ?1")
      DriverCarInfo findByDriveCarIDs(int id);

}
