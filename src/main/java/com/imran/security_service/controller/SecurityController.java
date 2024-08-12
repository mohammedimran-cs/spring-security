package com.imran.security_service.controller;

import com.imran.security_service.entity.Users;
import com.imran.security_service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String helloSecurity(){
        return "Hello Spring Security";
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return userService.verify(user);
    }
    @PostMapping("/addUser")
    public String addUser(@RequestBody Users user, final HttpServletRequest request){
         userService.addUser(user,request);
        return "success";
    }

    @GetMapping("/AllUsers")
    public List<Users> allUser(){
        return userService.getALlUsers();
    }
    @PutMapping("/update-user/{id}")
    public Users updateUser(@RequestBody Users user , @PathVariable("id") Long userId){
        return userService.updateUser(user,userId);
    }
}