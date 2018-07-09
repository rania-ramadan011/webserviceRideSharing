/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import WSInterfaces.DriverDao;
import WSInterfaces.MyUserDao;
import com.mycompany.ridesharingprows.DriverCarInfo;
import com.mycompany.ridesharingprows.User;
import java.util.Date;
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
public class DriverWs {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    DriverDao driverDao = context.getBean(DriverDao.class);
    MyUserDao userDao = context.getBean(MyUserDao.class);

    //---------------------------DriverSignup Service------------------------------
    @RequestMapping(value = "/driverSi9gnUpWs", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody

    public DriverCarInfo insertDriverObj(@RequestBody User user) {

        DriverCarInfo temp = user.getDriverCarInfo();
        user.setDriverCarInfo(null);
        user.setPending("0");
        user.setUserphoto("hhhcg");
        System.out.println(user.getUserName());
        User t = userDao.save(user);
        temp.setUserId(t);
        DriverCarInfo d = driverDao.save(temp);
        return d;

    }
    //----------------------------------------------------------------------------------

    @RequestMapping(value = "/getDriverCarInfo.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    DriverCarInfo getCarInfo(@RequestBody User u) 
    {
        
        User user = driverDao.findByUserIds(u.getIdUser());
        DriverCarInfo c = u.getDriverCarInfo();
        return c;
    }

}
