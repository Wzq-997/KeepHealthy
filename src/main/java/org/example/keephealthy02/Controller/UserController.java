package org.example.keephealthy02.Controller;

import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

//
}
