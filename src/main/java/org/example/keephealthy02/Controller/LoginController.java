package org.example.keephealthy02.Controller;

import io.jsonwebtoken.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.example.keephealthy02.Entity.Result;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Mapper.UserMapper;
import org.example.keephealthy02.Service.UserService;
import org.example.keephealthy02.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/login")
    public Result login(@RequestParam Integer id,@RequestParam String password) {

        User getuser = userService.getuser(id);
        if(getuser!=null)
        {
//            登录成功
            if(getuser.getPassword().equals(password))
            {
                Map<String, Object> claims = new HashMap<>();
                claims.put("id",getuser.getId());
                claims.put("name",getuser.getName());
                claims.put("birthday",getuser.getBirthday());
                claims.put("targetweight",getuser.getTargetweight());
                claims.put("pastMedicalHistory", getuser.getPastMedicalHistory());
                String s = JwtUtils.generateJwt(claims);//当前登录的用户信息
                return Result.success(s);
            }
        }
        return Result.error("密码错误!");
        //登录成功，生成令牌并下发令牌
        //登录失败，返回错误信息
//        if ("stulogin".equals(iden)) {
//            Student student = stuservice.stuLogin(id, passwd);
//            return student != null ? Result.success(student) : Result.error("登录失败！");
//        } else if ("managerlogin".equals(iden)) {
//            Manager manager = manService.manLogin(id, passwd);
//            return manager != null ? Result.success(manager) : Result.error("登录失败！");
//        } else {
//            Employee employee = empService.empLogin(id, passwd);
//            return employee != null ? Result.success(employee) : Result.error("登录失败！");
//        }
//    }
    }
}
