package org.example.keephealthy02.Controller;

import org.example.keephealthy02.Entity.Sleep;
import org.example.keephealthy02.Service.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sleep")
public class SleepController {

    @Autowired
    private SleepService sleepService;

    @PostMapping("/add")
    public void addSleep(@RequestBody Sleep sleep) {
        sleepService.addSleep(sleep);
    }

    @PostMapping("/addSelective")
    public void addSleepSelective(@RequestBody Sleep sleep) {
        sleepService.addSleepSelective(sleep);
    }

    @GetMapping("/get")
    public Sleep getSleepByUserIdAndDate(@RequestParam String userid, @RequestParam Date date) {
        return sleepService.getSleepByUserIdAndDate(userid, date);
    }

    @GetMapping("/all")
    public List<Sleep> getAllSleeps() {
        return sleepService.getAllSleeps();
    }

    @PutMapping("/update")
    public void updateSleep(@RequestBody Sleep sleep) {
        sleepService.updateSleep(sleep);
    }

    @DeleteMapping("/delete")
    public void deleteSleepByUserIdAndDate(@RequestParam String userid, @RequestParam Date date) {
        sleepService.deleteSleepByUserIdAndDate(userid, date);
    }
}
