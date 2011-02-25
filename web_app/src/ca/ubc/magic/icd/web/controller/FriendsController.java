package ca.ubc.magic.icd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class FriendsController {
 
    @RequestMapping("/friends")
    public ModelAndView homePage() {
    	String tester = "Friends PAGE KKTHX";
        return new ModelAndView("friends", "randomString", tester);
    	//return new ModelAndView("login");
    }
}
