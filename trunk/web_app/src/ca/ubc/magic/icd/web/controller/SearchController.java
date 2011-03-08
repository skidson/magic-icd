package ca.ubc.magic.icd.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.model.LinkManager;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.services.MagicService;
import ca.ubc.magic.icd.web.services.UserService;

@Controller
public class SearchController {
	@Autowired
	private MagicService magicService;
	
	@Autowired
	private LinkManager linkManager;

	@RequestMapping("/magic/userSearch")
	public ModelAndView userSearch(@RequestParam("searchQuery") String search) {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		
		List<User> searchResults = magicService.searchUser(search);
		model.put("searchResults", searchResults);
		return new ModelAndView("searchResult", model);
	}

	@RequestMapping("/bitSearch")
	public ModelAndView bitSearch(@RequestParam("searchQuery") String search,
			@RequestParam("searchFilter" )String filter) {
		// TODO implement some fancy search logic here
		return new ModelAndView("searchResult");
	}
	
}
