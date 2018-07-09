/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import WSInterfaces.MyUserDao;
import com.google.gson.Gson;
import com.mycompany.ridesharingprows.DriverCarInfo;


import com.mycompany.ridesharingprows.User;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Rania
 */
@Controller
public class UserWs {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    MyUserDao userDao = context.getBean(MyUserDao.class);
    private final String PATH ="C:\\savedimages\\";
    
    //Return user from Database By Email and Password---------------------------------------------
    @RequestMapping(value = "/getUserByEmailAndPassword.json", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    User getUserByEmailAndPassword(@RequestBody User object) {
        System.out.println("in Methos ByEMailAndPasword eeeeeeeeeeeeeeeeeee");
        User user = userDao.findByEMailAndPassword(object.getEMail(), object.getPassword()); 
        System.out.println("user name eeeeeeeeeeeeeeeeeeeeeee" + user.getUserName() + "aaaaaaaaaaaaaaa"+user.getDriverCarInfo());
        return user;

    }
    
    

    // insert user into DataBase<Registration>-------------------------------------------------
    @RequestMapping(value = "user2", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public User InsertUserDB2(@RequestParam("file") MultipartFile  uploadedInputStream ,@RequestParam("user") String user) {
        
//        
        
        Gson gson = new Gson();
        User user2 = gson.fromJson(user, User.class);
        
        System.out.println("user insert" + user2.getUserName());
        System.out.println("user insert email" + user2.getEMail());

        System.out.println("user insert" + user2.getGender());
        System.out.println("user insert" + user2.getMobile());
        System.out.println("user insert" + user2.getNationalid());
        System.out.println("user insert" + user2.getPassword());

        
        System.out.println("user insert" + user2.getUserphoto());

        System.out.println("user insert" + user2.getUserName());
        System.out.println("user insert" + user2.getUserName());

//        user2.setBirthDate( );
        user2.setPending("0");
        
        
        // check if all form parameters are provided
        if (uploadedInputStream == null)
        {   System.out.println("sssssss");
                return user2;
        }
        // create our destination folder, if it not exists
        Double name = Math.random()*10000000;
        String uploadedFileLocation = PATH+ name+".jpg"; 
        System.out.println(uploadedFileLocation);
        System.out.println(" ");
//        System.out.println("user insert" + user.getUserName());
        try {
                saveToFile(uploadedInputStream, uploadedFileLocation);
                user2.setUserphoto(uploadedFileLocation);
                userDao.save(user2);
        } catch (IOException e) {
                return null;
        }
        return user2;
        
    }
    private void saveToFile(MultipartFile  inStream, String target)throws IOException {
//        OutputStream out = new FileOutputStream(new File(target));
        
//        byte[] bytes = inStream.getBytes();
//        System.out.println(inStream.skip(read));
//        while ((read = inStream.(bytes)) != -1) {
//                out.write(bytes,0,read);
//        }
        
//        out.flush();
//        out.close();
        
        byte[] bytes = inStream.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(target)));
        stream.write(bytes);
        stream.close();
    }
    
    
    
      @RequestMapping(value = "/user.json", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public User InsertUserDB(@RequestBody User user) {

        System.out.println("user insert" + user.getUserName());
        System.out.println("user insert" + user.getEMail());

        System.out.println("user insert" + user.getGender());
        System.out.println("user insert" + user.getMobile());
        System.out.println("user insert" + user.getNationalid());
        System.out.println("user insert" + user.getPassword());

        user.setUserphoto("aaa");
        System.out.println("user insert" + user.getUserphoto());

        System.out.println("user insert" + user.getUserName());
        System.out.println("user insert" + user.getUserName());

       // user.setBirthDate(new Date());
        user.setPending("0");
        userDao.save(user);
        System.out.println("user insert" + user.getUserName());
        return user;
    }
     @RequestMapping(value = "getAllUser.json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<User> getAllUsers() {
        List<User> allUsers=new ArrayList<>();
        allUsers=(List<User>) userDao.findAllUsers();
        return allUsers;
        
    }
        
    
}
