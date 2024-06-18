package org.example.keephealthy02.Controller;

import org.example.keephealthy02.Entity.Userdiet;
import org.example.keephealthy02.Service.UserdietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/userdiet")
public class UserdietController {

    @Autowired
    private UserdietService userdietService;

    /**
     * 添加用户饮食记录
     * @param userdiet 用户饮食记录对象
     * @return 添加结果，返回影响的行数
     */
    @PostMapping("/add")
    public int addUserdiet(@RequestBody Userdiet userdiet) {
        return userdietService.addUserdiet(userdiet);
    }

    /**
     * 有选择性地添加用户饮食记录
     * @param userdiet 用户饮食记录对象
     * @return 添加结果，返回影响的行数
     */
    @PostMapping("/addSelective")
    public int addUserdietSelective(@RequestBody Userdiet userdiet) {
        return userdietService.addUserdietSelective(userdiet);
    }

    /**
     * 根据用户ID和日期删除用户饮食记录
     * @param userid 用户ID
     * @param date 日期
     * @return 删除结果，返回影响的行数
     */
    @DeleteMapping("/delete")
    public int deleteUserdietById(@RequestParam String userid, @RequestParam Date date) {
        return userdietService.deleteUserdietById(userid, date);
    }

    /**
     * 更新用户饮食记录
     * @param userdiet 用户饮食记录对象
     * @return 更新结果，返回影响的行数
     */
    @PutMapping("/update")
    public int updateUserdiet(@RequestBody Userdiet userdiet) {
        return userdietService.updateUserdiet(userdiet);
    }

    /**
     * 根据用户ID和日期获取用户饮食记录
     * @param userid 用户ID
     * @param date 日期
     * @return 返回用户饮食记录对象
     */
    @GetMapping("/get")
    public Userdiet getUserdietById(@RequestParam String userid, @RequestParam Date date) {
        return userdietService.getUserdietById(userid, date);
    }

    /**
     * 获取所有用户的饮食记录
     * @return 返回用户饮食记录对象的列表
     */
    @GetMapping("/all")
    public List<Userdiet> getAllUserdiets() {
        return userdietService.getAllUserdiets();
    }
}
