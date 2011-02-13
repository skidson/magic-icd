package ca.ubc.magic.icd.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ca.ubc.magic.icd.web.model.*;
 
@Controller
public class HomeController {
 
    @RequestMapping("/home")
    public ModelAndView homePage() {
 
        User steve = new User("Steve");
        List<String> friends = steve.getFriends();
        return new ModelAndView("home", "friends", friends);
    }
}
