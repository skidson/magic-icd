package ca.ubc.magic.icd.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.MagicService;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.services.UserService;

@Controller
public class HomeController {
	@Autowired
	private MagicService magicService;

	@RequestMapping("/home")
	public ModelAndView homePage() {
		Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
		return new ModelAndView("home", model);
	}

}
