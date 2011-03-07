package ca.ubc.magic.icd.web.controller;

import java.util.HashMap;
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
public class HomeController {
	@Autowired
	private MagicService magicService;

	@RequestMapping("/magic/home")
	public ModelAndView homePage() {
		Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
		
		JsonItem user1info = magicService.showUser(1);
		System.out.println(user1info.toString());
		JsonItem user2info = magicService.showUser(2);
		System.out.println(user2info.toString());
//		User user1 = new User(user1info.getAsString("name"), user1info.getAsString("username")
//								, user1info.getAsString("description")
//								, user1info.getAsInteger("experience")
//								, user1info.getAsInteger("points"));
		User user2 = new User(user2info.getAsString("name"), user2info.getAsString("username")
								, user2info.getAsString("description")
								, user2info.getAsInteger("experience")
								, user2info.getAsInteger("points"));
	//	model.put("user1", user1);
		model.put("user2", user2);
		return new ModelAndView("home", model);
	}

}
