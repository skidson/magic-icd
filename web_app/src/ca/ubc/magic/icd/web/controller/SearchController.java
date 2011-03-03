package ca.ubc.magic.icd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.MagicService;

@Controller
public class SearchController {
	private MagicService magicService;

	@RequestMapping("/friendSearch")
	public ModelAndView friendSearch() {

		return new ModelAndView("searchResult");
	}

	
	@RequestMapping("/bitSearch")
	public ModelAndView bitSearch(){
		return new ModelAndView("searchResult");
	}
	
	
	
	public void setMagicService(MagicService magicService) {
		this.magicService = magicService;
	}

	public MagicService getMagicService() {
		return magicService;
	}
}
