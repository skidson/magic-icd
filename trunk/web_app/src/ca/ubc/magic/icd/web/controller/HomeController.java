package ca.ubc.magic.icd.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.model.LinkManager;
import ca.ubc.magic.icd.web.services.MagicService;
import ca.ubc.magic.icd.web.services.UserService;

@Controller
public class HomeController {
	@Autowired
	private MagicService magicService;
	
	@Autowired
	private LinkManager linkManager;

	@RequestMapping("/magic/home")
	public ModelAndView magicHome() {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		return new ModelAndView("home", model);
	}
	
	@RequestMapping("/basic/home")
	public ModelAndView basicHome() {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		return new ModelAndView("home", model);
	}

}
