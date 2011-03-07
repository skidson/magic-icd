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
public class FriendController {
	@Autowired
	private MagicService magicService;
	
	@RequestMapping("/basic/friends")
	public ModelAndView basicFriends() {
		Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
		return new ModelAndView("friends", model);
	}

	@RequestMapping("/magic/friends")
	public ModelAndView magicFriends() {
		Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
		
		Iterator<JsonItem> iterator = magicService.showFriends().iterator();
		List<User> friendsList = new ArrayList<User>();
		while (iterator.hasNext()) {
			JsonItem currentUser = iterator.next();
			if(currentUser != null){
				User user = new User(currentUser.getAsJsonItem("user").getAsString("name"), currentUser.getAsJsonItem("user").getAsString("username")
						, currentUser.getAsJsonItem("user").getAsString("description")
						, 1
						, 1);
				user.setImageURL(currentUser.getAsJsonItem("user").getAsString("photo"));
				friendsList.add(user);
			}
			
		}
		for(User user: friendsList){
			System.out.println("Username:" + user.getUsername());
			System.out.println("name:" + user.getName());
			System.out.println("description:" + user.getDescription());
			System.out.println("photo:" + user.getImageURL() + "\n");
		}
		model.put("friendList", friendsList);
		return new ModelAndView("friends", model);
	}
	
	@RequestMapping("magic/createFriend")
	public ModelAndView createFriend(@RequestParam("friendID") int id){
		Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
		
		magicService.createFriend(id);
		
		return new ModelAndView("friends", model);
	}
	
}