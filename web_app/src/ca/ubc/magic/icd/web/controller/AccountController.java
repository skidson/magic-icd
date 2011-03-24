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
import ca.ubc.magic.icd.web.model.LinkManager;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.services.MagicService;
import ca.ubc.magic.icd.web.services.UserService;
 
/**
 * The Spring Controller that intercepts all URL patterns related to account.
 * Each function passes the specified jsp page name through the View Resolver to render it for the user
 * @author Jeffrey Payan
 *@author Stephen Kidson
 */
@Controller
public class AccountController {
	@Autowired
	MagicService magicService;
	
	@Autowired
	LinkManager linkManager;
	
	/**
	 * 
	 * 
	 */
	@RequestMapping("/basic/account")
	public ModelAndView basicAccount() {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		return new ModelAndView("account", model);
	}
	
	/**
	 * 
	 * 
	 */
    @RequestMapping("/magic/account")
    public ModelAndView magicAccount() {
    	Map<String, Object> model = UserService.initUserContext(linkManager);
		JsonItem profile = magicService.showUser();
		User user = new User(profile);
		model.put("profile", user);
		
		linkManager.linkMagic(true);
		model.put("linked", linkManager.getLinked());
		
		return new ModelAndView("account", model);
    }
    
    /**
     * 
     * 
     */
    @RequestMapping(value = "/basic/register", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("register");
	}
    
    /**
     * 
     * @param firstName - Request Parameter : in_firstName
     * @param lastName - Request Parameter : in_lastName
     * @param email - Request Parameter : in_email
     * @param username - Request Parameter : in_username
     * @param password - Request Parameter : in_password
     * @param confirmPassword - Request Parameter : in_confirmPassword
     * @param country - Request Parameter : in_country
     * 
     */
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
    
    /**
     * 
     * 
     */
    @RequestMapping("/basic/forgotPassword")
    public ModelAndView forgottenPassword(){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	return new ModelAndView("changePassword", model);
    }
    
    /**
     * 
     * @param oldPW - Request Parameter : oldPassword
     * @param newPW - Request Parameter : newPassword
     * @param confirmPW - Request Parameter : confirmPassword
     * @return
     */
    @RequestMapping("/basic/changePassword")
    public ModelAndView changePassword(@RequestParam("oldPassword") String oldPW,
    									@RequestParam("newPassword") String newPW,
    									@RequestParam("confirmPassword") String confirmPW){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	//check and change PW
    	return new ModelAndView("changePassword", model);
    }
    
    @RequestMapping("/mobile/android")
    public ModelAndView mobileAuthenticate(	@RequestParam("username") String username,
    										@RequestParam("password") String password) {
    	Map<String, Object> model = new HashMap<String, Object>();
    	if (UserService.authenticate(username, password)) {
    		User user = new User();
    		user.setUsername(username);
    		model.put("user", user);
    	}
    	// TODO populate model with CoffeeShop-specific user information here
    	return new ModelAndView("mobile", model);
    }
    
}