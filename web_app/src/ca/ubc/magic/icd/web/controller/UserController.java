package ca.ubc.magic.icd.web.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.Bit;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.oauth.CoffeeShopClient;
import ca.ubc.magic.icd.web.services.MagicService;

@Controller
public class UserController {
	@Autowired
	private MagicService magicService;
	
	@RequestMapping("asdfuser")
	public ModelAndView userPage() {
		JsonItem test = magicService.showUser(1);
		return new ModelAndView("account");
	}
}

