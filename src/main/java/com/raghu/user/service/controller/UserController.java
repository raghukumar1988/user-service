package com.raghu.user.service.controller;

import com.raghu.user.service.VO.ResponseTemplateVO;
import com.raghu.user.service.entity.User;
import com.raghu.user.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("Inside saveUser method of UserController class");
        return userService.saveUser(user);
    }

    @GetMapping("/{userId}")
    public ResponseTemplateVO getUserandDeparmentById(@PathVariable Long userId){
        log.info("Inside getUserandDeparmentById method of UserController class");
        return  userService.getUserandDeparmentById(userId);
    }
}
