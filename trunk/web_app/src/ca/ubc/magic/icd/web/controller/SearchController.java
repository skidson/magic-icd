package ca.ubc.magic.icd.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.services.MagicService;

@Controller
public class SearchController {
	@Autowired
	private MagicService magicService;

	@RequestMapping("/userSearch")
	public ModelAndView friendSearch(@RequestParam("searchQuery") String search) {
		String searchResult = magicService.searchUser(search).getAsString("username");

		return new ModelAndView("searchResult", "searchResult", searchResult);
	}

	@RequestMapping("/bitSearch")
	public ModelAndView bitSearch(){
		return new ModelAndView("searchResult");
	}
	
}
