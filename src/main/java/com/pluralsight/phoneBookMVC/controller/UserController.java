package com.pluralsight.phoneBookMVC.controller;

import com.pluralsight.phoneBookMVC.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser(@RequestParam(value = "firstname", defaultValue = "Gena") String firstname,
                        @RequestParam(value = "lastname", defaultValue = "Chursov") String lastname,
                        @RequestParam(value = "age", defaultValue = "30") int age) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user")
    public User postUser(User user) {
        System.out.println(user.getFirstname());
        return user;
    }
}
