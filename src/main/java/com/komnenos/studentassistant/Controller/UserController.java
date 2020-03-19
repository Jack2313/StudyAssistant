package com.komnenos.studentassistant.Controller;

import com.komnenos.studentassistant.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.komnenos.studentassistant.Entity.User;

@Controller
@CrossOrigin
@RequestMapping("/user/*")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value ="/register")
    @ResponseBody
    public int register(@RequestBody User user) {
        if(userRepository.findByUserName(user.getUserName())!=null)
        {
            return 0;
        }
        int result = 0;
        User user1=new User();
        user1.setUserName(user.getUserName());
        user1.setPassWord(user.getPassWord());
        userRepository.save(user1);
        result = 1;

        return result;
    }


    @GetMapping(value = "/login")
    @ResponseBody
    public int login(@RequestParam("username") String username, @RequestParam("password") String password) {

        User user=userRepository.findByNameAndCode(username, password);
        int result = 0;
        if(user!=null) {
            //session.setAttribute("userLogin", user);
            result = user.getUserId();
        }else {
            result = -1;
        }
        return result;
    }

    @GetMapping(value = "/getUser")
    @ResponseBody
    public String getUser(@RequestParam("userId") int userId){
        User u =userRepository.findByUserId(userId);
        return u.getUserName();
    }

    @GetMapping(value="/getPermission")
    @ResponseBody
    public int getPermission(@RequestParam("userId") int userId){
        User u =userRepository.findByUserId(userId);
        return u.getPermission();
    }
}
