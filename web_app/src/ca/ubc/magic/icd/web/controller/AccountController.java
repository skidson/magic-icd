package ca.ubc.magic.icd.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.LinkManager;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.services.MagicService;
import ca.ubc.magic.icd.web.services.UserService;
 
@Controller
public class AccountController {
	@Autowired
	MagicService magicService;
	
	@Autowired
	LinkManager linkManager;
	
	@RequestMapping("/basic/account")
	public ModelAndView basicAccount() {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		return new ModelAndView("account", model);
	}
	
    @RequestMapping("/magic/account")
    public ModelAndView magicAccount() {
    	Map<String, Object> model = UserService.initUserContext(linkManager);
		JsonItem profile = magicService.showUser();
		User user = new User(profile.getAsString("name"), 
				profile.getAsString("username"),
				profile.getAsString("description"),
				profile.getAsString("photo"),
				profile.getAsInteger("id"),
				profile.getAsInteger("experience"),
				profile.getAsInteger("points"));
		model.put("profile", user);
		
		linkManager.linkMagic(true);
		model.put("linked", linkManager.getLinked());
		
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
    
    @RequestMapping("/basic/forgotPassword")
    public ModelAndView forgottenPassword(){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	return new ModelAndView("changePassword", model);
    }
    
    @RequestMapping("/basic/changePassword")
    public ModelAndView changePassword(@RequestParam("oldPassword") String oldPW,
    									@RequestParam("newPassword") String newPW,
    									@RequestParam("confirmPassword") String confirmPW){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	//check and change PW
    	return new ModelAndView("changePassword", model);
    }
    
}