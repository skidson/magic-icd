package ca.ubc.magic.icd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class InboxController {
 
    @RequestMapping("/inbox")
    public ModelAndView homePage() {
    	String tester = "INBOX PAGE KKTHX";
        return new ModelAndView("inbox", "randomString", tester);
    	//return new ModelAndView("login");
    }
}
