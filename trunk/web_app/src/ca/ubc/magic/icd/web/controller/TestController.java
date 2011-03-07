package ca.ubc.magic.icd.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.services.MagicService;
import ca.ubc.magic.icd.web.services.UserService;

@Controller
public class TestController {
	@Autowired
	private MagicService magicService;

	@RequestMapping("/magic/test")
	public ModelAndView magicHome() {
		Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
		
		JsonItem userInfo = magicService.showUser(2);
		System.out.println(userInfo.getAsString("username"));
		System.out.println(userInfo.getAsInteger("id"));
		System.out.println(userInfo.getAsInteger("experience"));
		
		return new ModelAndView("home", model);
	}
	
}
