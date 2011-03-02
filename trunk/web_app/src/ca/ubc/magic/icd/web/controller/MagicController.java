package ca.ubc.magic.icd.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.MagicService;
import ca.ubc.magic.icd.web.json.JsonItem;

@Controller
public class MagicController {
	private MagicService magicService;

	@RequestMapping("/magic/friends")
	public ModelAndView getFriends() {
		List<JsonItem> friends = getMagicService().showFriends();
		List<String> friends_list = new ArrayList<String>();
		for (JsonItem friend : friends)
			friends_list.add(friend.toString());
		return new ModelAndView("friends", "friends_list", friends_list);
	}
	
	@RequestMapping("/magic/user")
	public ModelAndView getUser() {
		String user = getMagicService().showUser(1).toString();
		return new ModelAndView("account", "user", user);
	}
	
	@RequestMapping("/sparklr/photos.jsp")
	public ModelAndView getSpra() {
		String user = getMagicService().showUser(1).toString();
		return new ModelAndView("account", "photoIds", getMagicService().getSparklrPhotoIds());
	}
	
	public void setMagicService(MagicService magicService) {
		this.magicService = magicService;
	}

	public MagicService getMagicService() {
		return magicService;
	}
}