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
	
	@Autowired
	private LinkManager linkManager;

	@RequestMapping("/magic/userSearch")
	public ModelAndView userSearch(@RequestParam("searchQuery") String search) {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		
		List<User> searchResults = magicService.searchUser(search);
		JsonItem profile = magicService.showUser();
		User magicUser = new User(profile);
		model.put("magicUser", magicUser);
		model.put("usersFound", searchResults);
		return new ModelAndView("searchResult", model);
	}

	@RequestMapping("/magic/bitSearch")
	public ModelAndView bitSearch(@RequestParam("searchQuery") String search,
			@RequestParam("searchFilter" )String filter) {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		List<Bit> matchedType = new ArrayList<Bit>();
		List<Bit> matchedBits = new ArrayList<Bit>();
		for(int i = 1; i < 20; i++) {
    		try {
	    		JsonItem bitInfo = magicService.showBit(i);
	    		Bit bit = new Bit(bitInfo);
	    		if(filter.equals("all")) matchedType.add(bit);
	    		if(bit.getType().equalsIgnoreCase(filter)) matchedType.add(bit);
    		} catch (Exception e) {
    			continue;
    		}
    	}
		for(int i = 0; i < matchedType.size(); i++) {
    		try {
	    		if(matchedType.get(i).getName().toLowerCase().contains(search.toLowerCase())) matchedBits.add(matchedType.get(i));
    		} catch (Exception e) {
    			continue;
    		}
    	}
		JsonItem profile = magicService.showUser();
		User magicUser = new User(profile);
		model.put("magicUser", magicUser);
		model.put("bitsFound", matchedBits);
		return new ModelAndView("searchResult", model);
	}
	
}
