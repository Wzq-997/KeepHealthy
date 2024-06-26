package org.example.keephealthy02.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.example.keephealthy02.Entity.Result;
import org.example.keephealthy02.Entity.User;
import org.example.keephealthy02.Service.Impl.UserServiceImpl;
import org.example.keephealthy02.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/main")
@Api(tags = "登录管理Api")
public class LoginController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    /**
     * 生成claim
     */
    public Map<String,Object> generateClaim(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("name",user.getName());
        claims.put("password",user.getPassword());
        claims.put("birthday",user.getBirthday());
        claims.put("height",user.getHeight());
        claims.put("weight",user.getWeight());
        claims.put("targetweight",user.getTargetweight());
        claims.put("pastMedicalHistory", user.getPastMedicalHistory());

        return claims;
    }
    /**
     * login登录函数
     * @param userId 用户输入的账户
     * @param password 用户输入的密码
     * @param response 用于构建和修改Http请求
     * @return 结果：返回1，登录成功，同时携带token，返回0，登录失败
     */
    @ApiOperation(value = "登录",notes = "生成的token在headr的set-cookie下")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "userId",
                    value = "用户id",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "password",
                    value = "用户密码",
                    required = true
            )
    })
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestParam String userId, @RequestParam String password, HttpServletResponse response){
        User user =  userServiceImpl.getuser(userId);
        if(user != null && user.getPassword().equals(password)){// 生成 JWT
            String jwt = JwtUtils.generateJwt(generateClaim(user));//当前登录的用户信息

            // 创建一个 Cookie 并将 JWT 存入其中
            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setHttpOnly(true);
            // 在生产环境中确保通过 HTTPS 传输
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(1); // 设置 Cookie 的有效期为 1 小时
           response.addCookie(cookie);
            // 创建一个新的响应对象，包含其他信息
            Map<String, Object> respones = new HashMap<>();
            respones.put("date", LocalDateTime.now());
            return new Result(1,"登录成功", respones);
        }
        return new Result(0,"密码错误",LocalDateTime.now());
    }

    /**
     *
     * @param user user类对象
     * @return 返回1 注册成功  返回0 注册失败，用户已存在
     */
    @ApiOperation(value = "注册")
    @ApiImplicitParam(
            name = "user",
            value = "用户",
            required = true
    )
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public Result registe(@RequestBody User user) {
        // 检查用户是否已经存在
        User existingUser = userServiceImpl.selectById(user.getId());
        if (existingUser != null) {
            return new Result(1, "用户已存在", LocalDateTime.now());
        } else {
            userServiceImpl.insert(user);
            return new Result(0, "注册成功", LocalDateTime.now());
        }
    }

    /**
     *
     * @param user 要求更新的用户
     * @param response
     * @param token 需要携带jwt进行验证
     * @return
     */
    @ApiOperation(value = "验证用户是否已经登录成功")
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result update(@RequestBody User user,HttpServletResponse response,@RequestHeader("Authorization") String token){
        // 检查用户是否登录，验证 JWT
        if (JwtUtils.isTokenExpired(token)) {
            return new Result(1, "用户未登录或登录状态已过期",LocalDateTime.now());
        }

        // 更新用户信息
        userServiceImpl.update(user);

        // 在响应头中设置或更新 JWT
        String newToken = JwtUtils.generateJwt(generateClaim(user)); // 生成新的 JWT
        response.setHeader("Authorization", newToken); // 设置响应头中的 Authorization 字段

        // 设置或更新 Cookie
        Cookie jwtCookie = new Cookie("jwt", newToken);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(3600); // 设置 cookie 过期时间，这里设置为 1 小时
        response.addCookie(jwtCookie); // 添加到响应中

        return new Result(0, "用户信息更新成功",LocalDateTime.now());
    }
}
