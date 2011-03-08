package ca.ubc.magic.icd.web.controller;

import java.util.HashMap;
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
public class FriendController {
	@Autowired
	private MagicService magicService;
	
	@Autowired
	private LinkManager linkManager;
	
	@RequestMapping("/basic/friends")
	public ModelAndView basicFriends() {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		return new ModelAndView("friends", model);
	}

	@RequestMapping("/magic/friends")
	public ModelAndView magicFriends() {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		List<User> friendsList = magicService.showFriends();
		model.put("friendsList", friendsList);
		return new ModelAndView("friends", model);
	}
	
	@RequestMapping("magic/createFriend")
	public ModelAndView createFriend(@RequestParam("friendID") int id){
		Map<String, Object> model = UserService.initUserContext(linkManager);
		magicService.createFriend(id);
		return new ModelAndView("friends", model);
	}
	
}