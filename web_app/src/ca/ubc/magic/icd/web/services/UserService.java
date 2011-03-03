package ca.ubc.magic.icd.web.services;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;

import ca.ubc.magic.icd.web.model.User;

public class UserService {
	
	  public static void addUserContext(Map<String, Object> model) {
		  User user = new User();
		  user.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		  model.put("user", user);
	  }
}
