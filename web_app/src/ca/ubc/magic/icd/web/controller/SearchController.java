package ca.ubc.magic.icd.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.Bit;
import ca.ubc.magic.icd.web.model.LinkManager;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.services.MagicService;
import ca.ubc.magic.icd.web.services.UserService;

@Controller
public class SearchController {
	@Autowired
	private MagicService magicService;
	private static final int BITS_PER_PAGE = 15;
	@Autowired
	private LinkManager linkManager;

	@RequestMapping("/magic/userSearch")
	public ModelAndView userSearch(@RequestParam("searchQuery") String search) {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		
		List<User> searchResults = magicService.searchUser(search);
		List<User> currentFriends = magicService.showFriends();
		List<User> alreadyFriends = new ArrayList<User>();
		User magicUser = new User(magicService.showUser());
		
		model.put("magicUser", magicUser);
		model.put("usersFound", searchResults);
		
		for(User user : currentFriends){
			for(User searchedUser : searchResults){
				if(user.getUsername().equals(searchedUser.getUsername())) alreadyFriends.add(user);
			}
		}
		alreadyFriends.add(magicUser);
		model.put("alreadyFriends", alreadyFriends);
		return new ModelAndView("searchResult", model);
	}

	@RequestMapping("/magic/bitSearch")
	public ModelAndView bitSearch(@RequestParam("searchQuery") String search, @RequestParam(value = "page", required=false) Integer page) {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		int index;
		List<Bit> matchedBits = magicService.searchBits(search);
		List<Bit> toReturn = new ArrayList<Bit>();
		User magicUser = new User(magicService.showUser());
		
		if(page == null) {
    		page = 1;
    		index = BITS_PER_PAGE;
    	}
    	else index = (page-1)*BITS_PER_PAGE+BITS_PER_PAGE;
  
    	for (int i = (page-1) * BITS_PER_PAGE; i < matchedBits.size() && i < index; i++)
    		toReturn.add(matchedBits.get(i));
    	model.put("search", search);
		model.put("page", page);
		model.put("numPages", matchedBits.size()/BITS_PER_PAGE + 1);
		model.put("magicUser", magicUser);
		model.put("bitsFound", toReturn);
		return new ModelAndView("searchResult", model);
	}
	
	
}
