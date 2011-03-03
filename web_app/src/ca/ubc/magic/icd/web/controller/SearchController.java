package ca.ubc.magic.icd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.MagicService;
import ca.ubc.magic.icd.web.json.JsonItem;

@Controller
public class SearchController {
	private MagicService magicService;

	@RequestMapping("/userSearch")
	public ModelAndView friendSearch(@RequestParam("searchQuery") String search) {
		String searchResult = getMagicService().searchUser(search).getAsString("username");

		return new ModelAndView("searchResult", "searchResult", searchResult);
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
