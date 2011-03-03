package ca.ubc.magic.icd.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.MagicService;
import ca.ubc.magic.icd.web.controller.oauth.CoffeeShopService;
import ca.ubc.magic.icd.web.json.JsonItem;

@Controller
public class MagicController {
	@Autowired
	private MagicService magicService;

	@RequestMapping("friends")
	public ModelAndView getFriends() {
		List<JsonItem> friends = magicService.showFriends();
		List<String> friends_list = new ArrayList<String>();
		for (JsonItem friend : friends)
			friends_list.add(friend.toString());
		return new ModelAndView("friends", "friends_list", friends_list);
	}
	
	@RequestMapping("user")
	public ModelAndView getUser() {
		
		String user = magicService.showUser(1).toString();
		return new ModelAndView("friends", "magicuser", user);
	}
	
	@RequestMapping("photos.jsp")
	public ModelAndView getSparklrPhotoList() {
		Map<String, String> test = ((CoffeeShopService)magicService).getSparklrRestTemplate().getResource().getAdditionalParameters();
		if (test == null) {
			System.out.println("no additional parameters");
		} else {
			Iterator<Map.Entry<String, String>> iterator = test.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry<String, String> parameter = iterator.next();
				System.out.println(parameter.getKey() + " : " + parameter.getValue());
			}
		}
		return new ModelAndView("account", "photoIds", magicService.getSparklrPhotoIds());
	}
	
	@RequestMapping("photo")
	@ResponseBody
	public String getSparklrPhoto(@RequestParam("photo_id") String photoID) {
		InputStream photo = magicService.loadSparklrPhoto(photoID);
		if (photo == null) {
	        return "error";
		} else {
			StringBuilder builder = new StringBuilder();
			try {
		        while (photo.available() > 0) {
		        	builder.append(photo.read());
		        }
		        return builder.toString();
			} catch (IOException e) {
				return e.getMessage();
			}
		}	
	}
}
