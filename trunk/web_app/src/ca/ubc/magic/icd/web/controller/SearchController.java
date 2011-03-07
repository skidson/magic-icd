package ca.ubc.magic.icd.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.services.MagicService;
import ca.ubc.magic.icd.web.services.UserService;

@Controller
public class SearchController {
	@Autowired
	private MagicService magicService;

	@RequestMapping("/magic/userSearch")
	public ModelAndView userSearch(@RequestParam("searchQuery") String search) {
		Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
		
		List<User> searchResults = magicService.showFriends();
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
