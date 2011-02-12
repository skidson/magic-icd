package ca.ubc.magic.icd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class HomeController {
 
    @RequestMapping("/home")
    public ModelAndView homePage() {
 
        String message = "Jeff Succeded";
        return new ModelAndView("home", "message", message);
    }
}
