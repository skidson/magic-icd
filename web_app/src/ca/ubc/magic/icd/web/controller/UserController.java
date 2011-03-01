package ca.ubc.magic.icd.web.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.model.Bit;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.oauth.CoffeeShopClient;

@Controller
public class UserController {
	
	@RequestMapping("/userPage")
	public ModelAndView userPage() {
		CoffeeShopClient csc = new CoffeeShopClient();
		int userID = 1;
		User testuser = new User();
		testuser = csc.getUser(userID);
		
		return new ModelAndView("userPage", "testuser", testuser);
	}
}

