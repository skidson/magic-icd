package ca.ubc.magic.icd.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.model.LinkManager;
import ca.ubc.magic.icd.web.services.UserService;
 
@Controller
public class InboxController {
	@Autowired
	private LinkManager linkManager;
	
	@RequestMapping("/basic/inbox")
	public ModelAndView basicInbox() {
		Map<String, Object> model = UserService.initUserContext(linkManager);
		return new ModelAndView("inbox", model);
	}
 
    @RequestMapping("/magic/inbox")
    public ModelAndView magicInbox() {
    	Map<String, Object> model = UserService.initUserContext(linkManager);
		
		return new ModelAndView("inbox", model);
    }
}
