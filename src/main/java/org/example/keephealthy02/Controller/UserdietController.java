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

    @PostMapping("/add")
    public int addUserdiet(@RequestBody Userdiet userdiet) {
        return userdietService.addUserdiet(userdiet);
    }

    @PostMapping("/addSelective")
    public int addUserdietSelective(@RequestBody Userdiet userdiet) {
        return userdietService.addUserdietSelective(userdiet);
    }

    @DeleteMapping("/delete")
    public int deleteUserdietById(@RequestParam String userid, @RequestParam Date date) {
        return userdietService.deleteUserdietById(userid, date);
    }

    @PutMapping("/update")
    public int updateUserdiet(@RequestBody Userdiet userdiet) {
        return userdietService.updateUserdiet(userdiet);
    }

    @GetMapping("/get")
    public Userdiet getUserdietById(@RequestParam String userid, @RequestParam Date date) {
        return userdietService.getUserdietById(userid, date);
    }

    @GetMapping("/all")
    public List<Userdiet> getAllUserdiets() {
        return userdietService.getAllUserdiets();
    }
}
