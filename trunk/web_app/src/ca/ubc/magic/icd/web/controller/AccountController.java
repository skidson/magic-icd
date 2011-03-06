package ca.ubc.magic.icd.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.services.MagicService;
import ca.ubc.magic.icd.web.services.UserService;
 
@Controller
public class AccountController {
	@Autowired
	MagicService magicService;
    @RequestMapping("/basic/account")
    public ModelAndView accountPage() {
    	Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
		
		JsonItem userInfo = magicService.showUser();
		System.out.println(userInfo.toString());
		User user = new User(userInfo.getAsString("name"), userInfo.getAsString("username")
				, userInfo.getAsString("description")
				, userInfo.getAsInteger("experience")
				, userInfo.getAsInteger("points"));
		model.put("magicUser", user);
		return new ModelAndView("account", model);
    }
    
    @RequestMapping(value = "/basic/register", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("register");
	}
    
    @RequestMapping(value = "/basic/register", method = RequestMethod.POST)
	public ModelAndView registerUser(@RequestParam("in_firstName") String firstName,
			@RequestParam("in_lastName") String lastName,
			@RequestParam("in_email") String email,
			@RequestParam("in_username") String username,
			@RequestParam("in_password") String password,
			@RequestParam("in_confirmPassword") String confirmPassword,
			@RequestParam("in_country") String country) {
    	// TODO sanitize these inputs
    	StringBuilder error = new StringBuilder();
    	
    	if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
    			username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
    		error.append("* indicates a required field.\n");
    	
    	if (!password.equals(confirmPassword))
    		error.append("Passwords do not match.\n");
    	
    	// TODO check for illegal chars
    	
    	if (error.length() > 0)
    		return new ModelAndView("register", "error", error.toString());
    	
    	// TODO validate user with spring security
    	User user = new User();
    	user.setUsername(username);
		return new ModelAndView("register");
	}
    
}