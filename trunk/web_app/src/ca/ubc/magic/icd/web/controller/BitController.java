package ca.ubc.magic.icd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class BitController {
 
    @RequestMapping("/bits")
    public ModelAndView homePage() {
    	String tester = "BITS PAGE KKTHX";
        return new ModelAndView("bits", "randomString", tester);
    	//return new ModelAndView("login");
    }
}
