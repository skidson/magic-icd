package ca.ubc.magic.icd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.MagicService;

@Controller
public class HomeController {
	@Autowired
	private MagicService magicService;

	@RequestMapping("/home")
	public ModelAndView homePage() {
		return new ModelAndView("home");
	}

}
