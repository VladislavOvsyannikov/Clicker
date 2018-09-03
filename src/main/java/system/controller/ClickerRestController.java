package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import system.model.User;
import system.service.ClickerService;

import java.util.List;

@RestController
public class ClickerRestController {

    private ClickerService clickerService;

    @Autowired
    public void setClickerService(ClickerService clickerService) {
        this.clickerService = clickerService;
    }

    @RequestMapping(value = "/getUsername", method = RequestMethod.GET)
    public String getUsername(){
        return clickerService.getUserName();
    }

    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public User getCurrentUser(){
        return clickerService.getCurrentUser();
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getUsers(){
        return clickerService.getAllUsers();
    }

    @RequestMapping(value = "/getChampions", method = RequestMethod.GET)
    public List<User> getChampions(){
        return clickerService.getChampions();
    }

    @RequestMapping(value = "/click", method = RequestMethod.POST)
    public void click(){
        clickerService.click();
    }

    @RequestMapping(value = "/submitRegistration", method = RequestMethod.POST)
    public boolean shopRegistration(@RequestBody User user){
        return clickerService.addUser(user);
    }
}
