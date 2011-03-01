package ca.ubc.magic.icd.web.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.controller.oauth.CoffeeShopService;
import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.*;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public ModelAndView homePage() {
		CoffeeShopService css = new CoffeeShopService();
		
		JsonItem test = css.showBit(1);
		User user = new User();
		user.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		return new ModelAndView("home", "test", test);
		//return new ModelAndView("login");
	}
}
