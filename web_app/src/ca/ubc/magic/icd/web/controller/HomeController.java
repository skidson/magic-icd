package ca.ubc.magic.icd.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.MagicService;
import ca.ubc.magic.icd.web.controller.oauth.CoffeeShopService;
import ca.ubc.magic.icd.web.model.User;

@Controller
public class HomeController {
	private MagicService magicService;

	@RequestMapping("/home")
	public ModelAndView homePage() {
		CoffeeShopService css = new CoffeeShopService();
		
		String test = getMagicService().showUser(1).getAsString("user");
		User user = new User();
		user.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		return new ModelAndView("home", "test", test);
		//return new ModelAndView("login");
	}

	public void setMagicService(MagicService magicService) {
		this.magicService = magicService;
	}

	public MagicService getMagicService() {
		return magicService;
	}
}
