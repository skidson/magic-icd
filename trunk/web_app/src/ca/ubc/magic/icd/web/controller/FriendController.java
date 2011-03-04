package ca.ubc.magic.icd.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.services.MagicService;
import ca.ubc.magic.icd.web.services.UserService;

@Controller
public class FriendController {
	@Autowired
	private MagicService magicService;

	@RequestMapping("/magic/friends")
	public ModelAndView friendPage() {
		Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
		
		Iterator<JsonItem> iterator = magicService.showFriends().iterator();
		List<User> friendsList = new ArrayList<User>();
		while (iterator.hasNext()) {
			JsonItem currentUser = iterator.next();
			User user = new User(currentUser.getAsString("username"));
			user.setDescription(currentUser.getAsString("description"));
			user.setImageURL(currentUser.getAsString("photo"));
			user.setName(currentUser.getAsString("name"));
			friendsList.add(user);
		}
		
		model.put("friendList", friendsList);
		return new ModelAndView("friends", model);
	}
	
}