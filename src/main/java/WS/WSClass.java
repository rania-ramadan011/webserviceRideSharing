/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import WSInterfaces.MyUserDao;
import com.mycompany.ridesharingprows.User;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author TECHNOLOGY CITY
 */
@Controller
public class WSClass {

    @RequestMapping("/h")
    public @ResponseBody
    String sayHi() 
    {
        return "{'moustafa':'hegazy'}";
    }
    //--------------------------------------------------------------------------------

    @RequestMapping(value = "/getUser/{id}.json", method = RequestMethod.GET)
    public @ResponseBody
    User getUserx(@PathVariable(value = "id") Integer id) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MyUserDao inter = context.getBean(MyUserDao.class);
        System.out.println(id);
        User u = inter.findOne(id);
        System.out.println("------------------------------------------------");
        return u;

    }
//--------------------------------------------------------------------------------

    @RequestMapping(value = "/getUser/{userName}/{password}.json", method = RequestMethod.GET)
    public @ResponseBody
    User getUserx(@PathVariable(value = "userName") String userName,
            @PathVariable(value = "password") String password) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MyUserDao inter = context.getBean(MyUserDao.class);
        System.out.println(userName);
        User u = inter.LoginAdmin(userName, password);
        return u;

    }
    //--------------------------------------------------------------------------------

    @RequestMapping(value = "/getUser/pending.json", method = RequestMethod.GET)
    public @ResponseBody
    List<User> pending() {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MyUserDao inter = context.getBean(MyUserDao.class);
        List<User> u = inter.getAllPendingUsers();
        return u;

    }
    //--------------------------------------------------------------------------------

    @RequestMapping(value = "/getUser/AcceptReq/{id}.json", method = RequestMethod.GET)

    public @ResponseBody
    String Accept(@PathVariable(value = "id") int id) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MyUserDao inter = context.getBean(MyUserDao.class);
        inter.acceptUser(id);
        return "Done";

    }

    //--------------------------------------------------------------------------------
    @RequestMapping(value = "/getUser/DenyReq/{id}.json", method = RequestMethod.GET)
    public @ResponseBody
    String Deny(@PathVariable(value = "id") int id) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MyUserDao inter = context.getBean(MyUserDao.class);
        inter.denyUser(id);
        return "Done";

    }
    //--------------------------------------------------------------------------------

    @RequestMapping(value = "/getUser/DenyUsers.json", method = RequestMethod.GET)
    public @ResponseBody
    List<User> AllDenyUsers() {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MyUserDao inter = context.getBean(MyUserDao.class);
        List<User> u = inter.getAllDenyUsers();
        return u;

    }

}
