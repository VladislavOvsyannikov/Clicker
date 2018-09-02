package system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClickerController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "/home.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login.html";
    }

    @RequestMapping(value = "/highscore", method = RequestMethod.GET)
    public String highscore() {
        return "/highscore.html";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "/registration.html";
    }
}
