package ca.ubc.magic.icd.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.services.MagicService;

@Controller
public class SearchController {
	@Autowired
	private MagicService magicService;

	@RequestMapping("/magic/userSearch")
	public ModelAndView userSearch(@RequestParam("searchQuery") String search) {
		return new ModelAndView("searchResult", "searchResults", magicService.searchUser(search));
	}

	@RequestMapping("/bitSearch")
	public ModelAndView bitSearch(@RequestParam("searchQuery") String search,
			@RequestParam("searchFilter" )String filter) {
		// TODO implement some fancy search logic here
		return new ModelAndView("searchResult");
	}
	
}
