package ca.ubc.magic.icd.web.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.model.Bit;
import ca.ubc.magic.icd.web.model.LinkManager;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.services.MagicService;
import ca.ubc.magic.icd.web.services.UserService;
/**
 * The Spring Controller that intercepts all URL patterns related to users/friends
 * Each function passes the specified jsp page name to the View Resolver to render it for the user
 * @author Jeffrey Payan
 * @author Stephen Kidson
 */
@Controller
public class FriendController {
	@Autowired
	private MagicService magicService;
	
	@Autowired
	private LinkManager linkManager;
	
	/**
	 * 
	 * 
	 */
	@RequestMapping("/basic/friends")
	public ModelAndView basicFriends() {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		return new ModelAndView("friends", model);
	}
	
	/**
	 * 
	 * 
	 */
	@RequestMapping("/magic/friends")
	public ModelAndView magicFriends() {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		List<User> friendsList = magicService.showFriends();
		model.put("friendsList", friendsList);
		return new ModelAndView("friends", model);
	}
	
	/**
	 * 
	 * @param id - Request Parameter : friendID
	 * 
	 */
	@RequestMapping("magic/createFriend")
	public ModelAndView createFriend(@RequestParam("friendID") int id){
		Map<String, Object> model = UserService.initUserContext(linkManager);
		magicService.createFriend(id);
		return new ModelAndView("redirect:/magic/friends", model);
	}
	
	/**
	 * 
	 * @param id - Request Parameter :userID
	 * 
	 */
	@RequestMapping("magic/userPage")
	public ModelAndView getUserPage(@RequestParam("userID") int id){
		Map<String, Object> model = UserService.initUserContext(linkManager);
		String alreadyFriend = "false";
		Random r = new Random();
		User friend = new User(magicService.showUser(id));
		List<User> magicFriends = magicService.showFriends((new User(magicService.showUser())).getId());
		List<Bit> linkedBits = magicService.showBitLinksOfUser(id);
		List<User> friends = magicService.showFriends(id);
		if(friends.size() > 0) {
			User randomFriend = friends.get(r.nextInt(friends.size()));
			model.put("randomFriend", randomFriend);
		}
		
		if(linkedBits.size() > 0){
			model.put("linkedBits", linkedBits);
		}
		
		for(User magicFriend : magicFriends){
			if (friend.getUsername().equals(magicFriend.getUsername())){
			alreadyFriend = "true";
			break;
			}
		}
		
		model.put("alreadyFriend", alreadyFriend);
		model.put("friend", friend);
		return new ModelAndView("userPage", model);
	}
	
	/**
	 * 
	 * @param id - Request Parameter : friendID
	 * 
	 */
	@RequestMapping("magic/destroyFriend")
	public ModelAndView destroyFriend(@RequestParam("friendID") int id){
		Map<String, Object> model = UserService.initUserContext(linkManager);
		
		magicService.destroyFriend(id);
		
		return new ModelAndView("redirect:/magic/friends", model);
	}
	
}