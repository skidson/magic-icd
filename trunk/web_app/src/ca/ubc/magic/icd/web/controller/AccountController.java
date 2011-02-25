package ca.ubc.magic.icd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class AccountController {
 
    @RequestMapping("/account")
    public ModelAndView homePage() {
    	String tester = "ACCOUNT PAGE KKTHX";
        return new ModelAndView("account", "randomString", tester);
    	//return new ModelAndView("login");
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("register");
	}
}
