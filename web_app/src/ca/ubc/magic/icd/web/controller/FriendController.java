package ca.ubc.magic.icd.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.services.MagicService;

@Controller
public class FriendController {
	@Autowired
	private MagicService magicService;

	@RequestMapping("/basic/friends")
	public ModelAndView friendPage() {
		Iterator<JsonItem> friendsitr = magicService.showFriends().iterator();
		List<User> friendsList = new ArrayList<User>();
		while(friendsitr.hasNext()){
			JsonItem currentUser = friendsitr.next();
			User user = new User(currentUser.getAsString("username"));
			user.setDescription(currentUser.getAsString("description"));
			user.setImageURL(currentUser.getAsString("photo"));
			user.setName(currentUser.getAsString("name"));
			friendsList.add(user);
		}
		return new ModelAndView("friends", "friendList", friendsList);
	}
}
