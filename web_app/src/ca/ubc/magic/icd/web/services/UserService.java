package ca.ubc.magic.icd.web.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;

import ca.ubc.magic.icd.web.model.LinkManager;
import ca.ubc.magic.icd.web.model.User;
/**
 * 
 * @author Jeffrey Payan
 *@author Stephen Kidson
 */
public class UserService {

	/**
	 * Create a new user based on the current user logged into the application
	 * and add it to the model for the current page being viewed.
	 * @param model - the model to add the user to
	 * @return A model with the new user added to it.
	 */
	public static void addUserContext(Map<String, Object> model) {
		User user = new User();
		user.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("user", user);
	}
	
	/**
	 * Create a new user based on the current user logged into the application
	 * and add it to the model for the current page being viewed. Also adds the current "Linked" status
	 * to the model
	 * @param model - the model to add the user to
	 * @return A model with the new user and linked object added to it.
	 * @see ca.ubc.magic.icd.web.model.Linked
	 */
	public static Map<String, Object> initUserContext(LinkManager linkManager) {
		Map<String, Object> model = new HashMap<String, Object>();
		User user = new User();
		user.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("user", user);
		model.put("linked", linkManager.getLinked());
		return model;
	}
	
}
