package ca.ubc.magic.icd.web.controller;

import java.util.HashMap;
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
        List<Bit> bits = steve.getBits();
        HashMap relationships = new HashMap();
        relationships.put("friends", friends);
        relationships.put("bits", bits);
        return new ModelAndView("home", "relationships", relationships);
    }
}
