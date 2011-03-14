package ca.ubc.magic.icd.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping("/magic/viewMessage")
    public ModelAndView viewMessage(@RequestParam("message_id") int id){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	if(id == 1){
    		String message_contents = "Welcome to the Coffee Shop Client, have a good day!";
    		model.put("message_contents", message_contents);
    	}else if(id == 2){
    		String message_contents = "Hey, was just wondering if you wanted to go out for coffe some time. Lemme know!";
    		model.put("message_contents", message_contents);
    	}
    	return new ModelAndView("message", model);
    }
}
