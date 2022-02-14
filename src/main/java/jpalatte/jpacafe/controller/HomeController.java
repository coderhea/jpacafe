package jpalatte.jpacafe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {


//    @RequestMapping("/login")
//    public String login() {
//        return "login";
//    }

    @RequestMapping({"/home","/"})  // / to /home, /
    public String home() {
        log.info("home controller");
        return "home";
    }
}
