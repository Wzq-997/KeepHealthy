package org.example.keephealthy02.Controller;

import io.swagger.annotations.Api;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户管理Api")
public class UserController {
    @Autowired
    private UserService userService;

//
}
