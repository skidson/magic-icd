package ca.ubc.magic.icd.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.services.UserService;
 
@Controller
public class InboxController {
	
	@RequestMapping("/basic/inbox")
	public ModelAndView basicInbox() {
		Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
		return new ModelAndView("inbox", model);
	}
 
    @RequestMapping("/magic/inbox")
    public ModelAndView magicInbox() {
    	Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
		
		return new ModelAndView("inbox", model);
    }
}
